package it.tagetik.apps.third.project.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiError {
    private final int status;
    private final String message;


}
