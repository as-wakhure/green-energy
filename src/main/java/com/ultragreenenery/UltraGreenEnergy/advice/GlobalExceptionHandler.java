package com.ultragreenenery.UltraGreenEnergy.advice;

import com.ultragreenenery.UltraGreenEnergy.exceptions.UserAlreadyExistException;
import com.ultragreenenery.UltraGreenEnergy.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException exception){

        Map<String,Object> exceptionMap=new LinkedHashMap<>();
        exceptionMap.put("timestamp", LocalDateTime.now());
        exceptionMap.put("status",409);
        exceptionMap.put("message",exception.getMessage());
        return  ResponseEntity.status(HttpStatus.OK).body(exceptionMap);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex){
        System.out.println("ex message= "+ex.getMessage());
        ex.printStackTrace();
        Map<String,Object> exceptionMap=new LinkedHashMap<>();
        exceptionMap.put("timestamp", new Date());
        exceptionMap.put("status",500);
        exceptionMap.put("message","Something went wrong!");
        return  ResponseEntity.status(HttpStatus.OK).body(exceptionMap);

    }

}
