package com.lff.connector.dto;

public record UserResponse(
    String first_name,
    String last_name,
    String login,
    String email
) {

}
