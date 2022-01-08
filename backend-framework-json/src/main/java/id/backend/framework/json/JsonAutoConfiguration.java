package id.backend.framework.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.backend.framework.json.helper.JsonHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonAutoConfiguration {

    @Bean
    public JsonHelper jsonHelper(ObjectMapper objectMapper) {
        return new JsonHelper(objectMapper);
    }

}
