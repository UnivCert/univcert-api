package com.univcert.backend.error;

public class UnivNotFoundException extends RuntimeException{
    public static String UNIV_NOT_FOUND ="존재하지 않는 유저를 조회하고 있습니다.";
    public UnivNotFoundException(String message) {
        super(message);
    }
    public UnivNotFoundException() {
        super(UNIV_NOT_FOUND);
    }
}
