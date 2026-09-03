package com.lff.connector.dto;

public record UserResponse(
    String first_name,
    String last_name,
    String login,
    String email
) {
    public static UserResponse from(com.lff.connector.domain.UserDomain user) {
        return new UserResponse(
            user.getFirstName(),
            user.getLastName(),
            user.getLogin(),
            user.getEmail()
        );
    }
}
