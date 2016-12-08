package com.infiniot.web.model;

/**
 * In order to handle different situations and provide complet test cases to other modules of our
 * project, `PING32J` has a list of running mode.
 * 
 * @author mincong-h
 * @see https://github.com/mincong-h/ping32/wiki/PING32J-running-mode
 */
public final class Mode {

  public static final int OFF = 0; // Refuse all operations
  public static final int FIRE_RANDOM = 1; // Generate random fire
  public static final int FROM_PING32M = 4; // Receive data from Ping32M
}
