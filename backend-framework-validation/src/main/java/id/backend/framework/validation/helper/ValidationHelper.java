package id.backend.framework.validation.helper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

public class ValidationHelper {

    private final Validator validator;

    public ValidationHelper(Validator validator) {
        this.validator = validator;
    }

    public void validate(Object object) {
        Set<ConstraintViolation<Object>> result = validator.validate(object);
        if (!result.isEmpty()) {
            throw new ConstraintViolationException(result);
        }
    }

}
