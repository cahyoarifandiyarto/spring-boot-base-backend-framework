package id.backend.framework.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.backend.framework.json.helper.JsonHelper;
import id.backend.framework.json.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JsonAutoConfigurationTest.Application.class)
public class JsonAutoConfigurationTest {

    private ObjectMapper objectMapper;

    private JsonHelper jsonHelper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setJsonHelper(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    @Test
    void initTest() {
        Assertions.assertNotNull(objectMapper);
        Assertions.assertNotNull(jsonHelper);
    }

    @Test
    void fromJsonSuccess() throws JsonProcessingException {
        String json = "{\"firstName\":\"Cahyo Arif\",\"lastName\":\"Andiyarto\"}";
        User user = jsonHelper.fromJson(json, User.class);
        User user1 = objectMapper.readValue(json, User.class);
        Assertions.assertEquals(user, user1);
    }

    @Test
    void fromJsonException() {
        String json = "{\"firstName\":\"Cahyo Arif\",\"lastName\":\"Andiyarto\",\"fullName\":\"Cahyo Arif Andiyarto\"}";
        Assertions.assertThrows(JsonProcessingException.class, () -> {
           User user = jsonHelper.fromJson(json, User.class);
           User user1 = objectMapper.readValue(json, User.class);
        });
    }

    @Test
    void fromJsonTypeRefSuccess() throws JsonProcessingException {
        String json = "[{\"firstName\":\"Cahyo Arif\",\"lastName\":\"Andiyarto\"}]";
        List<User> users = jsonHelper.fromJson(json, new TypeReference<List<User>>() {});
        List<User> users1 = objectMapper.readValue(json, new TypeReference<List<User>>() {});
        Assertions.assertEquals(users.size(), 1);
        Assertions.assertEquals(users1.size(), 1);
        Assertions.assertEquals(users.get(0).getFirstName(), users1.get(0).getFirstName());
        Assertions.assertEquals(users.get(0).getLastName(), users1.get(0).getLastName());
    }

    @Test
    void fromJsonTypeRefException() {
        String json = "[{\"firstName\":\"Cahyo Arif\",\"lastName\":\"Andiyarto\",\"fullName\":\"Cahyo Arif Andiyarto\"}]";
        Assertions.assertThrows(JsonProcessingException.class, () -> {
            List<User> users = jsonHelper.fromJson(json, new TypeReference<List<User>>() {});
            List<User> users1 = objectMapper.readValue(json, new TypeReference<List<User>>() {});
        });
    }

    @Test
    void toJsonSuccess() throws JsonProcessingException {
        User user = User.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .build();

        String expected = jsonHelper.toJson(user);
        String actual = objectMapper.writeValueAsString(user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toJsonException() {
        Assertions.assertThrows(JsonProcessingException.class, () -> {
           String json = jsonHelper.toJson(new Object());
           String json1 = objectMapper.writeValueAsString(new Object());
        });
    }

    @SpringBootApplication
    public static class Application {

    }

}
