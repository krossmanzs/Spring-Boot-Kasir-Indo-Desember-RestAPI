package kasir.indo.desember.kasirIndoDesember.exception.error;

import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.time.ZonedDateTime;

public class ErrorDetails {
    private HttpStatus httpStatus;
    private ZonedDateTime timeStamp;
    private String message;
    private String details;

    public ErrorDetails(HttpStatus httpStatus, ZonedDateTime timeStamp, String message, String details) {
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
