package id.backend.framework.json.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonHelper {

    private final ObjectMapper objectMapper;

    public JsonHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public <T> T fromJson(String content, Class<T> valueType) {
        return objectMapper.readValue(content, valueType);
    }

    @SneakyThrows
    public <T> T fromJson(String content, TypeReference<T> valueTypeRef) {
        return objectMapper.readValue(content, valueTypeRef);
    }

    @SneakyThrows
    public String toJson(Object value) {
        return objectMapper.writeValueAsString(value);
    }

}
