package br.com.simplecrud.api.exception.handler;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.JWTDecodeException;

import br.com.simplecrud.api.exception.JwtAuthenticationException;
import br.com.simplecrud.api.exception.PatternException;
import br.com.simplecrud.api.exception.ValidException;
import br.com.simplecrud.api.util.Constants;
import br.com.simplecrud.api.util.Messages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public static final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .userMessages(Arrays.asList(ex.getMessage()))
                .devMessage(ExceptionUtils.getRootCauseMessage(ex))
                .description(request.getDescription(false))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build());
    }

    @ExceptionHandler(value = { UsernameNotFoundException.class })
    public static final ResponseEntity<ExceptionResponse> handleExceptionsNotFound(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse.builder()
                .userMessages(Arrays.asList(ex.getMessage()))
                .devMessage(ExceptionUtils.getRootCauseMessage(ex))
                .description(request.getDescription(false))
                .status(HttpStatus.NOT_FOUND.value())
                .build());
    }

    @ExceptionHandler({ JwtAuthenticationException.class, JWTDecodeException.class, AccessDeniedException.class })
    public static final ResponseEntity<ExceptionResponse> handleExceptionsForbidden(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ExceptionResponse.builder()
                .userMessages(Arrays.asList(ex.getMessage()))
                .devMessage(ExceptionUtils.getRootCauseMessage(ex))
                .description(request.getDescription(false))
                .status(HttpStatus.FORBIDDEN.value())
                .build());
    }

    @ExceptionHandler(value = { PatternException.class })
    public static final ResponseEntity<ExceptionResponse> handlePatternExceptions(PatternException ex,
            WebRequest request) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ExceptionResponse.builder()
                .userMessages(Arrays.asList(ex.getMessage()))
                .devMessage(ExceptionUtils.getRootCauseMessage(ex))
                .description(request.getDescription(false))
                .status(ex.getHttpStatus().value())
                .build());
    }

    @ExceptionHandler(value = { ValidException.class })
    public static final ResponseEntity<ExceptionResponse> handleValidExceptions(ValidException ex, WebRequest request) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ExceptionResponse.builder()
                .userMessages(ex.getErrs())
                .devMessage(ExceptionUtils.getRootCauseMessage(ex))
                .description(request.getDescription(false))
                .status(ex.getHttpStatus().value())
                .build());
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleValidationExceptions(ex, request);
    }

    private static final ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex,
            WebRequest request) {
        final List<String> userMessages = ex.getBindingResult().getAllErrors().stream()
                .map((ObjectError error) -> {
                    String err = error.getDefaultMessage();
                    if (err != null && err.contains(Constants.JOKER)) {
                        err = err.replace(Constants.JOKER,
                                Messages.getMessage(String.format("%s.%s", error.getObjectName(),
                                        ((FieldError) error).getField())));
                    }
                    return err;
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.builder()
                .userMessages(userMessages)
                .devMessage(ExceptionUtils.getRootCauseMessage(ex))
                .description(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST.value())
                .build());
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ExceptionResponse {

    private List<String> userMessages;
    private String devMessage;
    private String description;

    @Builder.Default
    private Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    @Builder.Default
    private Date date = new Date();

    public String getDateFormat() {
        return date != null ? new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date) : "-";
    }
}
