package managers;

import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fastcgi.client.FCGIClient;
import fastcgi.response.FCGIResponse;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("ClassCanBeRecord")
public class MyEntityManager implements EntityManager {
    private final FCGIClient fcgiClient;

    public MyEntityManager(FCGIClient fcgiClient) {
        this.fcgiClient = fcgiClient;
    }

    private Map<String, String> genParams(int contentLength) {
        Map<String, String> params = new HashMap<>();
        params.put("GATEWAY_INTERFACE", "FastCGI/1.0");
        params.put("REQUEST_METHOD", "POST");
        params.put("CONTENT_TYPE", "application/json");
        params.put("HTTP_CONTENT_LENGTH", Integer.toString(contentLength));
        params.put("ACTION", "find");
        return params;
    }

    private <T> JsonObject genObjMapping(Class<T> clazz) {
        JsonObject objMapping = new JsonObject();

        objMapping.addProperty("className", clazz.getSimpleName());

        String tableName = clazz.getSimpleName();
        if (clazz.isAnnotationPresent(Table.class) && !clazz.getAnnotation(Table.class).name().isEmpty()) {
            tableName = clazz.getAnnotation(Table.class).name();
        }
        objMapping.addProperty("tableName", tableName);

        JsonElement classMapping = new Gson().toJsonTree(
                Stream.of(clazz.getDeclaredFields())
                        .collect(Collectors.toMap(Field::getName, field -> {
                            if (field.isAnnotationPresent(Column.class) && !field.getAnnotation(Column.class).name().isEmpty()) {
                                return field.getAnnotation(Column.class).name();
                            } else {
                                return field.getName();
                            }
                        })));
        objMapping.add("class", classMapping);

        Optional<Field> primaryKeyField = Stream.of(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst();
        if (primaryKeyField.isEmpty()) {
            throw new RuntimeException(clazz.getSimpleName() +  ": primary key not found!");
        }
        objMapping.addProperty("primaryKey", primaryKeyField.get().getName());
        return objMapping;
    }

    private <T> String genJsonBody(String action, Object actionBody, Class<T> clazz) {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("action", action);

        if (actionBody instanceof String) {
            jsonBody.addProperty("actionBody", (String) actionBody);
        } else if (actionBody instanceof JsonObject) {
            jsonBody.add("actionBody", (JsonObject) actionBody);
        }

        jsonBody.add("objectMapping", genObjMapping(clazz));
        return jsonBody.toString();
    }

    @Override
    public boolean persist(Object obj) {
        String content = genJsonBody("persist", new Gson().toJsonTree(obj), obj.getClass());
        FCGIResponse fcgiResponse = fcgiClient.request(genParams(content.length()), content);

        return new Gson().fromJson(new String(fcgiResponse.getResponseContent()), boolean.class);
    }

    @Override
    public <T> T find(Class<T> clazz, String key) {
        String content = genJsonBody("find", key, clazz);
        FCGIResponse fcgiResponse = fcgiClient.request(genParams(content.length()), content);

        return new Gson().fromJson(new String(fcgiResponse.getResponseContent()), clazz);
    }
}
