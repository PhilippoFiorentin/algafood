package com.philippo.algafood.api.exceptionhandler;

import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null){
            body = Problem.builder()
                    .dateHour(LocalDateTime.now())
                    .message(status.getReasonPhrase()).build();
        } else if (body instanceof String){
            body = Problem.builder()
                    .dateHour(LocalDateTime.now())
                    .message((String) body).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
