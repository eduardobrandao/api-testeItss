package com.itss.crud.core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> treatsEntityNotFoundException(
            EntidadeNaoEncontradaException e, WebRequest request
    ) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemTypeEnum problemTypeEnum = ProblemTypeEnum.ENTIDADE_NAO_ENCONTRADA;
        String datail = e.getMessage();
        Problem response = createProblemBuilder(
                httpStatus,
                problemTypeEnum,
                datail).build();

        return handleExceptionInternal(e, response, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> entityInUse(
            EntidadeEmUsoException e, WebRequest request
    ) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(body == null) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();

        } else if(body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(e, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemTypeEnum problemTypeEnum = ProblemTypeEnum.INVALID_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = ex.getBindingResult();

        List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> Problem.Field.builder()
                        .name(fieldError.getField())
                        .userMessage(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemTypeEnum, detail)
                .userMessage(detail)
                .fields(problemFields)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private  Problem.ProblemBuilder createProblemBuilder(
            HttpStatus status,
            ProblemTypeEnum problemTypeEnum,
            String detail) {

        return Problem.builder()
                .status(status.value())
                .type(problemTypeEnum.getUri())
                .title(problemTypeEnum.getTitle())
                .detail(detail);
    }
}


