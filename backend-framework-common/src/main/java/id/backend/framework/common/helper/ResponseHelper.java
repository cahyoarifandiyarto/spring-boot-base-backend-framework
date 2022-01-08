package id.backend.framework.common.helper;

import id.backend.framework.common.model.response.Error;
import id.backend.framework.common.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHelper {

    public <T> ResponseEntity<Response<T>> buildResponse(HttpStatus httpStatus, T data) {
        Response<T> response = buildResponseCodeAndStatus(httpStatus);
        response.setData(data);

        return buildResponseEntity(httpStatus, response);
    }

    public <T> ResponseEntity<Response<?>> buildResponse(HttpStatus httpStatus, List<Error> errors) {
        Response<T> response = buildResponseCodeAndStatus(httpStatus);
        response.setErrors(errors);

        return buildResponseEntity(httpStatus, response);
    }

    private <T> Response<T> buildResponseCodeAndStatus(HttpStatus httpStatus) {
        return Response.<T>builder()
                .code(httpStatus.value())
                .status(httpStatus.name())
                .build();
    }

    private <T> ResponseEntity<T> buildResponseEntity(HttpStatus httpStatus, T body) {
        return ResponseEntity
                .status(httpStatus)
                .body(body);
    }

}
