package kasir.indo.desember.kasirIndoDesember.exception.error;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiErrror {
    private HttpStatus status;
    private ZonedDateTime timeStamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    public ApiErrror() {
        timeStamp = ZonedDateTime.now();
    }

    public ApiErrror(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiErrror(HttpStatus status, Throwable e) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = e.getLocalizedMessage();
    }

    public ApiErrror(HttpStatus status, String message, Throwable e) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = e.getLocalizedMessage();
    }

    // util for subErrors
    private void addSubError(ApiSubError subError) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }
        this.subErrors.add(subError);
    }

    private void addValidationError(
            String object,
            String field,
            Object rejectedValue,
            String message
    ) {
        addSubError(new ApiValidationError(
                object, message
        ));
    }
}
