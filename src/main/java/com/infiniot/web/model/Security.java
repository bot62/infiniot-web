package com.infiniot.web.model;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.infiniot.web.exception.ForbiddenException;

/**
 * Security class provides methods to check whether a HTTP request has the legitimacy before
 * processing the server resources.
 * 
 * @author mincong-h
 */
public final class Security {

  public static String AJAX_ACCESS_KEY = "72cec25cbbd386c3b68f9b73f480057ef55d09b8"; // SHA1("Ping32J")
  private static final String ERR_ACCESS_KEY_WRONG = "Access denied. Key wrong.";
  private static final String ERR_ACCESS_KEY_EMPTY = "Access denied. Key required.";

  /**
   * Construct JSON object representing a given error.
   * 
   * @param errorCode error code
   * @param errorMessage error message
   * @return error JSON object
   */
  public static JSONObject getError(int errorCode, String errorMessage) {

    JSONObject error = new JSONObject();
    error.put("error", errorCode);
    error.put("message", errorMessage);
    return error;

  }

  /**
   * Check the validity of the provided key.
   * 
   * @param key Access key encrypted in SHA-1
   * @return isChecked
   */
  public static boolean check(String key) {

    if (key == null) {
      throw new NullPointerException(ERR_ACCESS_KEY_EMPTY);
    } else if (!key.equals(AJAX_ACCESS_KEY)) {
      throw new SecurityException(ERR_ACCESS_KEY_WRONG);
    } else {
      return true;
    }

  }

  /**
   * Check the login of the user.
   * 
   * @param session the current http session
   * @throws ForbiddenException access is forbidden by the server if not login
   */
  public static void check(HttpSession session) throws ForbiddenException {
    if (session == null) {
      throw new ForbiddenException();
    }
  }
}
