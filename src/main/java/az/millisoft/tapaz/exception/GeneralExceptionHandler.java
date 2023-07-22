package az.millisoft.tapaz.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFound notFound){
        return new ResponseEntity<>(
                notFound.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException validation){
        return new ResponseEntity<>(
                validation.getFieldError().getDefaultMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception exception){
        return new ResponseEntity<>(
                "Gozlenilmeyen xeta bash verdi",
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
