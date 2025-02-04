package edu.baylor.cs.junit.exception;

public final class StringUtils {
 
  public static Integer convertToInt(String str) {
    if (str == null || str.trim().length() == 0) {
      throw new IllegalArgumentException("String must be not null or empty");
    }
    return Integer.valueOf(str);
  }
}