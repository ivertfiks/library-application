package com.libraryapp.security;

import com.libraryapp.security.util.RandomKeyGeneratior;


public class SecurityConstants {

    public static final String REGISTRATION_PATH = "/users/register";
    public static final String LOGIN_PATH = "/users/login";
    public static final Long TOKEN_EXPIRATION = 7200000L;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private static RandomKeyGeneratior RandomKeyGenerator;
    public static final String SECRET_KEY = RandomKeyGenerator.generateRandomKey();

    public static final String LOGOUT_PATH = "/logout";

}
