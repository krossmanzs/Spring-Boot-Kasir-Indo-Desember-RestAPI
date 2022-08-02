package kasir.indo.desember.kasirIndoDesember.exception.handler;

import kasir.indo.desember.kasirIndoDesember.exception.BadRequestException;
import kasir.indo.desember.kasirIndoDesember.exception.ConflictException;
import kasir.indo.desember.kasirIndoDesember.exception.error.ErrorDetails;
import kasir.indo.desember.kasirIndoDesember.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

// tell spring that this class will be serve to handling multiple exception
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // let tell spring that this method will responsible handling ResourceNotFoundException
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException e,
            WebRequest request
    ) {
        // 1 Create payload containing exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ErrorDetails errorDetails = new ErrorDetails(
                notFound,
                ZonedDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        // 2. return the actual response entity
        return new ResponseEntity<>(errorDetails, notFound);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(
            BadRequestException e,
            WebRequest request
    ) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorDetails errorDetails = new ErrorDetails(
                badRequest,
                ZonedDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, badRequest);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<Object> handleConflictException(
            ConflictException e,
            WebRequest request
    ) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ErrorDetails errorDetails = new ErrorDetails(
                conflict,
                ZonedDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, conflict);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("httpStatus", status);
        errorMap.put("timeStamp", ZonedDateTime.now());
        errorMap.put("message", "expected invalid input format");

        return new ResponseEntity<>(
                errorMap,
                status
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
                status,
                ZonedDateTime.now(),
                "Unexpected error occurred",
                ex.getLocalizedMessage()
        );

        return new ResponseEntity<>(
                errorDetails,
                status
        );
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGlobalException(
            Exception e,
            WebRequest request
    ) {

        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDetails errorDetails = new ErrorDetails(
                internalServerError,
                ZonedDateTime.now(),
                e.toString(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, internalServerError);
    }
}
