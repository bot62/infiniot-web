package com.infiniot.web.model;

/**
 * In order to handle different situations and provide complet test cases to other modules of our
 * project, `PING32J` has a list of running mode.
 * 
 * @author mincong-h
 * @see https://github.com/mincong-h/ping32/wiki/PING32J-running-mode
 */
public final class Mode {

  /**
   * Refuse all operations
   */
  public static final int OFF = 0;

  /**
   * Generate random fire
   */
  public static final int FIRE_RANDOM = 1;

  /**
   * Receive data from HTTP / HTTPS requests
   */
  public static final int FROM_HTTP = 2;
}
