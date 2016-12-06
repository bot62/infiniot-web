package com.infiniot.web.exception;

/**
 * Security exception will be raised when security key is not provided by HTTP request. This key is
 * the string "Ping32J" encrypted in SHA-1.
 * 
 * @author mincong-h
 */
public class SecurityException extends Exception {

  private static final long serialVersionUID = -103347685696558179L;
  private int code;
  private String message;

  public SecurityException(String message) {
    // super(message);
    this.message = message;
    this.code = ExceptionCode.SECURITY;
  }

  public String getCode() {
    return String.valueOf(code);
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
