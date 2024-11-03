package org.sopt.diary.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({CustomException.class, NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleException(Exception e){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = determineStatus(e);

        Map<String,Object> errors = new HashMap<>();
        errors.put("Code", getCode(e) );
        errors.put("ErrorMessage", getErrorMessage(e));
        errors.put("Date", String.valueOf(new Date()));

        return new ResponseEntity<>(errors, headers, status);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private HttpStatus determineStatus(Exception e){
        if(e instanceof CustomException){
            return ((CustomException) e).getErrorCode().getHttpStatus();
        } else if (e instanceof NoHandlerFoundException){
            return HttpStatus.NOT_FOUND;
        } else if(e instanceof HttpRequestMethodNotSupportedException){
            return HttpStatus.METHOD_NOT_ALLOWED;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private int getCode(Exception e){
        if(e instanceof CustomException){
            return ((CustomException) e).getHttpErrorCode();
        }else if ( e instanceof NoHandlerFoundException){
            return HttpStatus.NOT_FOUND.value();
        }else if (e instanceof HttpRequestMethodNotSupportedException){
            return HttpStatus.METHOD_NOT_ALLOWED.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String getErrorMessage(Exception e){
        if(e instanceof CustomException){
            return ((CustomException) e).getErrorCode().getMessage();
        } else if(e instanceof NoHandlerFoundException){
            return "해당 요청을 찾을 수 없습니다";
        } else if(e instanceof HttpRequestMethodNotSupportedException){
            return "지원되지 않는 HTTP 메서드입니다";
        }

        return "Internal ServerError";
    }



}
