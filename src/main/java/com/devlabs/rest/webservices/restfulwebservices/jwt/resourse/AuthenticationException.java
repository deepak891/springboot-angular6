package com.devlabs.rest.webservices.restfulwebservices.jwt.resourse;

public class AuthenticationException  extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}