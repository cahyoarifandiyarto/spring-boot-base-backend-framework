package id.backend.framework.validation;

import id.backend.framework.validation.helper.ValidationHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validator;

@Configuration
public class ValidationAutoConfiguration {

    @Bean
    public ValidationHelper validationHelper(Validator validator) {
        return new ValidationHelper(validator);
    }

}
