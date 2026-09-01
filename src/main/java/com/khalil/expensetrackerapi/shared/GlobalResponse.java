package com.khalil.expensetrackerapi.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class GlobalResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;

    public static <T> ResponseEntity<GlobalResponse<T>> success(T data,
                                                                String message,
                                                                HttpStatus status) {

        return ResponseEntity.status(status).body(new GlobalResponse<>(true,
                data,
                message

        ));
    }

    public static <T> ResponseEntity<GlobalResponse<T>> error(T data,
                                                              String message,
                                                              HttpStatus status) {

        return ResponseEntity.status(status).body(new GlobalResponse<>(false,
                data,
                message
        ));
    }
}
