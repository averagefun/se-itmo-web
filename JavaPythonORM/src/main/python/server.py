from socketserver import TCPServer
from fastcgi import FcgiHandler
import json

from db import Database

HOST = 'localhost'
PORT = 42085


class MyHandler(FcgiHandler):

    def setup(self):
        super().setup()

        # noinspection PyAttributeOutsideInit
        self.db = Database(hostname='', database='postgres',
                           username='postgres', password='')
        print('connected')

    def handle(self):
        print('REQUEST:')
        print('headers:', self.environ)

        body = json.loads(self['stdin'].read().decode('utf-8'))
        print('stdin:', body)

        response = self.action_handler(body)

        self['stdout'].write(str.encode(json.dumps(response)))

    def action_handler(self, body):
        action = body['action']

        obj_mapping = body['objectMapping']
        primary_key = obj_mapping['primaryKey']
        primary_key_column = obj_mapping['class'][primary_key]
        primary_key_value = body['actionBody']
        table_name = obj_mapping['tableName']

        response = {
            'success': False,
            'body': None
        }

        if action == 'find':
            results = self.db.fetchall(f"SELECT * FROM {table_name} WHERE {primary_key_column} = %s",
                                           primary_key_value)
            if not results:
                return response
            result = results[0]

            response_obj = {}
            for tableColumn in result:
                entity_key = list(obj_mapping['class'].keys())[list(obj_mapping['class'].values()).index(tableColumn)]
                response_obj[entity_key] = result[tableColumn]

            response['success'] = True
            response['body'] = response_obj
            return response
        elif action == 'persist':
            column_value = dict()
            for key in body['actionBody']:
                column_name = obj_mapping['class'][key]
                column_value[column_name] = body['actionBody'][key]

            columns = ', '.join([key for key in column_value.keys()])
            values = [value for value in column_value.values()]
            subs = ', '.join(['%s'] * len(values))
            try:
                self.db.update(f"INSERT INTO {table_name} ({columns}) VALUES ({subs})", values)
                response['success'] = True
            finally:
                return response
        return response


with TCPServer((HOST, PORT), MyHandler) as srv:
    print(f'Python FastCgi server started at {HOST}:{PORT}')
    srv.serve_forever()
