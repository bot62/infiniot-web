package com.infiniot.web.exception;

/**
 * Forbidden Exception indicates that an access to a resource requested by a client has been
 * forbidden by the server.
 * 
 * @see http://docs.oracle.com/javaee/7/api/javax/ws/rs/ForbiddenException.html
 */
public class ForbiddenException extends Exception {

  private static final long serialVersionUID = -5759157065240148557L;

  public ForbiddenException() {
    super("Please login.");
  }

  public ForbiddenException(String message) {
    super(message);
  }
}
