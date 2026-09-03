package com.lff.connector.handlers;

import java.time.LocalDateTime;

public record ResponseError(
    LocalDateTime timestamp,
    int status,
    String error,
    String message
) {

}
