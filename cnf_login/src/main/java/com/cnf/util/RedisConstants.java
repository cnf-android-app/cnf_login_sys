package com.cnf.util;

public class RedisConstants {

  public static final String LOGIN_USER_KEY_PREFIX = "login:user:token:";
  public static final Long LOGIN_USER_TTL = 18000L;
  public static final String AUTH_PERIOD_KEY_PREFIX = "login:auth:token:";
  public static final Long AUTH_PERIOD_TTL = 18000L;
  public static final String USER_ID_KEY = "user";
  public static final String MUNI_ID_KEY = "muni";
  public static final String HTTP_AUTHORIZATION_HEADER_NAME = "authorization";
}
