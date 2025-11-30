package com.onar.eymen.common.core.constant;

public final class Codes {
  private Codes() {}

  public static final String UNEXPECTED = "5000";
  public static final String METHOD_ARGUMENT_NOT_VALID = "1200";
  public static final String ILLEGAL_ARGUMENT = "2800";
  public static final String NO_SUCH_ELEMENT = "3100";

  public static final class Product {
    private Product() {}

    public static final String PRODUCT_ALREADY_EXIST = "P1000";
    public static final String PRODUCT_NOT_EXIST = "P1001";
    public static final String INSUFFICIENT_STOCK = "P1002";
  }

  public static final class Categories {
    private Categories() {}

    public static final String CATEGORIES_ALREADY_EXIST = "C1000";
    public static final String CATEGORY_HAS_SUB_CATEGORIES = "C1001";
    public static final String CIRCULAR_REFERENCE = "C1002";
    public static final String PARENT_CATEGORY_NOT_EXIST = "C1003";
    public static final String CATEGORY_NOT_FOUND = "C1004";
  }

  public static final class User {
    private User() {}

    public static final String BREACHED_PASSWORD = "U1001";
    public static final String EMAIL_ALREADY_EXISTS = "U1002";
    public static final String OLD_PASSWORD_MISMATCH = "U1004";
    public static final String NOT_FOUND = "U1003";
  }

  public static final class Auth {
    private Auth() {}

    public static final String INVALID_JWT = "S1000";
    public static final String TOKEN_TYPE_MISMATCH = "S1001";
  }

  public static final class Role {
    private Role() {}

    public static final String NOT_FOUND = "R1000";
  }
}
