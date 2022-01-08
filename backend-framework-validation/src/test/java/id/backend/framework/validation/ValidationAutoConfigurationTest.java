package id.backend.framework.validation;

import id.backend.framework.validation.helper.ValidationHelper;
import id.backend.framework.validation.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest(classes = ValidationAutoConfigurationTest.Application.class)
public class ValidationAutoConfigurationTest {

    private ValidationHelper validationHelper;

    @Autowired
    public void setValidationHelper(ValidationHelper validationHelper) {
        this.validationHelper = validationHelper;
    }

    @Test
    void initTest() {
        Assertions.assertNotNull(validationHelper);
    }

    @Test
    void validateSuccess() {
        User user = User.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .build();
        Assertions.assertDoesNotThrow(() -> {
            validationHelper.validate(user);
        });
    }

    @Test
    void validateException() {
        User user = User.builder()
                .firstName("")
                .lastName("")
                .build();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            validationHelper.validate(user);
        });
    }

    @SpringBootApplication
    public static class Application {

    }

}
