package id.backend.framework.common.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error implements Serializable {

    private static final long serialVersionUID = -3517342817412153224L;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

}
