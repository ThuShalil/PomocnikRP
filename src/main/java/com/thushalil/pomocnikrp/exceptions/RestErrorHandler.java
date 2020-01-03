package com.thushalil.pomocnikrp.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "spring.datasource.url")
@Order(Ordered.LOWEST_PRECEDENCE
)
public class RestErrorHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> handle(Exception ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.ok().build();
    }



}


