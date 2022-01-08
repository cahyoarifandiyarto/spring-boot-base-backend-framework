package id.backend.framework.common;

import id.backend.framework.common.helper.ResponseHelper;
import id.backend.framework.common.model.User;
import id.backend.framework.common.model.response.Error;
import id.backend.framework.common.model.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(classes = CommonAutoConfigurationTest.Application.class)
public class CommonAutoConfigurationTest {

    private ResponseHelper responseHelper;

    @Autowired
    public void setResponseHelper(ResponseHelper responseHelper) {
        this.responseHelper = responseHelper;
    }

    @Test
    void initTest() {
        Assertions.assertNotNull(responseHelper);
    }

    @Test
    void buildResponseDataSuccess() {
        User user = User.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .build();

        Response<User> response = Response.<User>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(user)
                .build();

        ResponseEntity<Response<User>> actual = ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

        ResponseEntity<Response<User>> expected = responseHelper.buildResponse(HttpStatus.OK, user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void buildResponseErrorSuccess() {
        Error error = Error.builder()
                .code(1001)
                .message("Invalid Request")
                .build();

        List<Error> errors = List.of(error);

        Response<?> response = Response.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();

        ResponseEntity<Response<?>> actual = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

        ResponseEntity<Response<?>> expected = responseHelper.buildResponse(HttpStatus.BAD_REQUEST, errors);

        Assertions.assertEquals(expected, actual);
    }

    @SpringBootApplication
    public static class Application {}

}
