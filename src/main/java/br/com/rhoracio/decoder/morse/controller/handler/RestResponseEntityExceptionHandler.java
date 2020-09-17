package br.com.rhoracio.decoder.morse.controller.handler;

import java.util.ArrayList;
import java.util.List;

import br.com.rhoracio.decoder.morse.domain.exception.ErrorDescription;
import br.com.rhoracio.decoder.morse.domain.exception.FieldErrorDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<FieldErrorDescription> details = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(field -> {
            details.add(
                    FieldErrorDescription.builder()
                        .field(field.getField())
                        .description(field.getDefaultMessage())
                        .build());

        });

        return new ResponseEntity<>(
                ErrorDescription.builder()
                        .errors(details)
                        .path(((ServletWebRequest) request).getRequest().getServletPath())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}