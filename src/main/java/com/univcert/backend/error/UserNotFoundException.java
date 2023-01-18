package com.univcert.backend.error;

public class UserNotFoundException extends RuntimeException {

    public static String USER_NOT_FOUND ="존재하지 않는 유저를 조회하고 있습니다.";
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
