package learning.quarkus.notes.configuration.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseWrapper <T> {
    private String status;
    @JsonProperty("error-code")
    private String errorCode;

    @JsonProperty("error-message")
    private String errorMessage;
    private T data;
}