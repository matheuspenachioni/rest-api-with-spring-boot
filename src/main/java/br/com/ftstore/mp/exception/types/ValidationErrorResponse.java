package br.com.ftstore.mp.exception.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<FieldError> errors;

    public ValidationErrorResponse() {
        this.timestamp = LocalDateTime.now();
        this.status = 400;
        this.error = "Bad Request";
        this.errors = new ArrayList<>();
    }

    public void addError(String field, String message) {
        FieldError fieldError = new FieldError(field, message);
        errors.add(fieldError);
    }

    @Data
    private static class FieldError {
        private String field;
        private String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}