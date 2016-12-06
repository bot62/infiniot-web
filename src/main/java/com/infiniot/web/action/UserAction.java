package com.infiniot.web.action;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import com.infiniot.web.exception.ExceptionCode;
import com.infiniot.web.model.Security;
import com.infiniot.web.model.User;
import com.infiniot.web.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author minconghuang
 */
public class UserAction extends ActionSupport {

  private static final long serialVersionUID = -4324071825692129452L;
  private final String JSON = "json";
  private final String ERR_ACCESS_KEY_WRONG = "Access denied. Access key wrong.";
  private final String ERR_ACCESS_KEY_EMPTY = "Access denied. Access key required.";
  private final String ERR_AUTH_USER_NOT_FOUND = "Utilisateur inconnu";
  private final String ERR_AUTH_WRONG_PASS = "Mot de passe incorrect";

  private LoginService loginService;

  public String execute() {
    // some complex business logic
    return SUCCESS;
  }

  public void validate() {
    // add SHA check here
  }

  /**
   * AJAX request for getting user' info, but password is not included. These informations are sent
   * back by JSON in HTTP response. This method is used by Android application.
   * 
   * @see https://github.com/mincong-h/ping32/wiki/APIs
   * @return null
   * @throws IOException if JSON can not be written in HTTP response
   */
  public String getUser() throws IOException {
    HttpServletRequest request = ServletActionContext.getRequest(); // HTTP req
    HttpServletResponse response = ServletActionContext.getResponse(); // HTTP resp
    response.setContentType("text/html;charset=UTF-8");
    String email = request.getParameter("email"); // input email
    String password = request.getParameter("password"); // input password
    String key = request.getParameter("key");
    JSONObject json = new JSONObject(); // output json

    try {

      // security: access key check
      if (key == null) {
        throw new NullPointerException(ERR_ACCESS_KEY_EMPTY);
      } else if (!key.equals(Security.AJAX_ACCESS_KEY)) {
        throw new SecurityException(ERR_ACCESS_KEY_WRONG);
      }

      Map<String, Object> map = new Hashtable<String, Object>(); // output map
      User u = loginService.getUser(email);
      // if user is not found in database
      // result is false, user null and return an error message
      if (u == null) {
        map.put("result", "0");
        map.put("user", "null");
        map.put("error", ERR_AUTH_USER_NOT_FOUND);

        // if user is found in database, but password incorrect
        // then result is false, user null and return an error message
      } else if (!u.getPassword().equals(password)) {
        map.put("result", "0");
        map.put("user", "null");
        map.put("error", ERR_AUTH_WRONG_PASS);

        // if everything OK, result is true
      } else {
        map.put("result", "1");
        // password is denied for this request
        u.setPassword(null);
        map.put("user", u);
      }
      json = new JSONObject(map);

    } catch (SecurityException | NullPointerException e) {

      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json = error;

    } finally {
      request.setAttribute(JSON, json);
    }
    return JSON;
  }

  /**
   * Action for user's sign in
   * 
   * @return String value indicating forward URL
   */
  public String signin() {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    User user = new User();
    user.setEmail(request.getParameter("login-email"));
    user.setPassword(request.getParameter("login-password"));
    if (loginService.isLoginCorrect(user)) {
      session.setAttribute("user", user);
      return SUCCESS;
    } else {
      return ERROR;
    }
  }

  /**
   * Action for user's sign up
   * 
   * @return String value indicating forward URL
   */
  public String signup() {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    User user = new User();
    // TODO:add setters for complete user profile
    if (loginService.isLoginCorrect(user)) {
      session.setAttribute("user", user);
      return SUCCESS;
    } else {
      return ERROR;
    }
  }

  /**
   * Action for user's sign out
   * 
   * @return String value indicating forward URL
   */
  public String signout() {
    HttpServletRequest request = ServletActionContext.getRequest();
    request.getSession().setAttribute("user", null);
    return SUCCESS;
  }

  /**
   * @return the loginService
   */
  public LoginService getLoginService() {
    return loginService;
  }

  /**
   * @param loginService the loginService to set
   */
  public void setLoginService(LoginService loginService) {
    this.loginService = loginService;
  }

}
