package org.sopt.diary.exception;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.http.HttpStatus;


public enum ErrorCode {
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"40900","이메일 중복입니다" ),
    NICK_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "40901", "닉네임 중복입니다"),
    INVALID_EMAIL_TYPE(HttpStatus.UNAUTHORIZED, "40000", "이메일 형식이 올바르지 않습니다"),
    INVALID_SIZE(HttpStatus.UNAUTHORIZED, "40001", "글자수를 확인하세요"),
    INVALID_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "40500", "지원하지 않는 HTTP 메소드입니다"),
    INVALID_USER(HttpStatus.UNAUTHORIZED, "40100", "비밀번호가 잘못되었습니다"),
    NON_AUTHORIZED(HttpStatus.UNAUTHORIZED, "40300", "권한이 없습니다");


    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
