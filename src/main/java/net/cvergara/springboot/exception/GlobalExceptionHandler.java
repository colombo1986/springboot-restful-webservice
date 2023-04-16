package net.cvergara.springboot.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                         WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now() ,
                exception.getMessage() ,
                webRequest.getDescription(false) ,
                "USER_NOT_FOUND"
        ) ;

        return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND) ;

    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handlerEmailAlreadyExistsException(EmailAlreadyExistsException exception,
                                                                         WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now() ,
                exception.getMessage() ,
                webRequest.getDescription(false) ,
                "EMAIL_ALREADY_EXISTS"
        ) ;

        return new ResponseEntity<>(errorDetails , HttpStatus.BAD_REQUEST) ;

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handlerGlobalException(Exception exception,
                                                                           WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now() ,
                exception.getMessage() ,
                webRequest.getDescription(false) ,
                "INTERNAL_SERVER_ERROR"
        ) ;

        return new ResponseEntity<>(errorDetails , HttpStatus.INTERNAL_SERVER_ERROR) ;

    }
    
       @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
                                                                  HttpHeaders headers, 
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
