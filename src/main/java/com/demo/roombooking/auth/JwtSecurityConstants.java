package com.demo.roombooking.auth;

public final class JwtSecurityConstants {

  public static final String AUTH_LOGIN_URL = "/auth";
  public static final String TOKEN_HEADER = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String TOKEN_TYPE = "JWT";
  public static final String TOKEN_ISSUER = "room-booking-apis";
  public static final String TOKEN_AUDIENCE = "room-booking-apps";
  public static final String JWT_SECRET =
      "secret81662817!@#1$%%$$$54442121zsx321#####!!!!666r111110000MbQeThWmZq4t7w!z%C*F-J@NcRf";

  private JwtSecurityConstants() {}
}
