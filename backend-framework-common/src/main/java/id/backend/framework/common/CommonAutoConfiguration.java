package id.backend.framework.common;

import id.backend.framework.common.helper.ResponseHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonAutoConfiguration {

    @Bean
    public ResponseHelper responseHelper() {
        return new ResponseHelper();
    }

}
