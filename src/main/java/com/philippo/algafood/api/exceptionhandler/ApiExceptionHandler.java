package com.philippo.algafood.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String FINAL_USER_GENERIC_ERROR_MESSAGE = "An unexpected problem has occurred in the system. " +
            "Try again and if the problem persists, contact your system administrator.";

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException)
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        else if(rootCause instanceof PropertyBindingException)
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);

        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = "Invalid request body. Check syntax error.";

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType type = ProblemType.BUSINESS_ERROR;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, type, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex, WebRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType type = ProblemType.ENTITY_IN_USE;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, type, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = FINAL_USER_GENERIC_ERROR_MESSAGE;

        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null){
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                    .build();
        } else if (body instanceof String){
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException)
            return handleArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {

        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = String.format("The resource '%s' does not exist", ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();

        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "One or more fields are invalid. Please fill in the fields correctly and try again.";

        List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                    return Problem.Field.builder()
                            .name(fieldError.getField())
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage("One or more fields are invalid. Please fill in the fields correctly and try again.")
                .fields(problemFields)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                       HttpHeaders headers,
                                                                       HttpStatus status,
                                                                       WebRequest request){

        ProblemType problemType = ProblemType.INVALID_PARAMETER;
        String detail = String.format("The URL parameter '%s' received the value '%s', which belongs to an invalid type. " +
                "Enter a value compatible with the type %s.",
                ex.getName(),
                ex.getValue(),
                ex.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        String path = joinPath(ex.getPath());

        ProblemType type = ProblemType.MESSAGE_NOT_READABLE;
        String detail = String.format(
                "The property '%s' does not exist. Fix this property or remove it and try again.", path);

        Problem problem = createProblemBuilder(status, type, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers,
                                                       HttpStatus status,
                                                       WebRequest request) {

        String path = joinPath(ex.getPath());

        ProblemType type = ProblemType.UNKNOWN_PROPERTY;
        String detail = String.format(
                "The property '%s' has been given the value '%s' which is of an invalid type. " +
                        "Enter a value compatible with type '%s’.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, type, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timestamp(LocalDateTime.now());
    }
}
