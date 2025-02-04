package edu.baylor.cs.junit.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Ex7Exception {
 
  @Test
  public void convertToIntNullParameterAssertThrows() {
    String st = null;
    assertThrows(IllegalArgumentException.class, () -> {
      StringUtils.convertToInt(st);
    });
  }
 
}