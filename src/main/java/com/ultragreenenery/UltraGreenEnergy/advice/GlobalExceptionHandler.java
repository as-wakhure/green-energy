package com.ultragreenenery.UltraGreenEnergy.advice;

import com.ultragreenenery.UltraGreenEnergy.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception){
        Map<String,Object> exceptionMap=new LinkedHashMap<>();
        exceptionMap.put("timestamp",new Date());
        exceptionMap.put("status",404);
        exceptionMap.put("message",exception.getMessage());
        return  ResponseEntity.status(HttpStatus.OK).body(exceptionMap);

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex){
        Map<String,Object> exceptionMap=new LinkedHashMap<>();
        exceptionMap.put("timestamp", new Date());
        exceptionMap.put("status",500);
        exceptionMap.put("message","Something went wrong!");
        return  ResponseEntity.status(HttpStatus.OK).body(exceptionMap);

    }

}
