import psycopg2


class Database:
    def __init__(self, hostname, database, username, password):
        self.conn = psycopg2.connect(host=hostname, user=username, password=password, dbname=database)
        self.conn.autocommit = True
        self.cursor = self.conn.cursor()

    @staticmethod
    def format_subs(substitutions):
        if not isinstance(substitutions, (tuple, list)):
            return substitutions,
        return substitutions

    def fetchall(self, query, substitutions=()):
        self.cursor.execute(query, self.format_subs(substitutions))

        results = []
        columns = [desc[0] for desc in self.cursor.description]
        for row in self.cursor.fetchall():
            results.append(dict(zip(columns, row)))
        return results

    def update(self, query, substitutions=()):
        self.cursor.execute(query, self.format_subs(substitutions))
