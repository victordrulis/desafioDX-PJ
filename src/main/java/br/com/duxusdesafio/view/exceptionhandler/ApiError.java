package br.com.duxusdesafio.view.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private final HttpStatus status;
    private final int satusCode;
    private final String path;
    private final String message;
    private final List<String> errors;

    public ApiError(HttpStatus status, int satusCode, String path, String message, String error) {
        super();
        this.status = status;
        this.satusCode = satusCode;
        this.path = path;
        this.message = message;
        errors = Collections.singletonList(error);
    }
}
