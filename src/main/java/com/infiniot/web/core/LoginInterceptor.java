package com.infiniot.web.core;

import java.util.Map;

import com.infiniot.web.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Interceptor for login. If user is not found in session, than request will be rejected by struts 2
 * before arriving to controler. This intereceptor is only used in the web client side, android side
 * uses security key instead of login interception.
 * 
 * @author mincong-h
 */
public class LoginInterceptor extends MethodFilterInterceptor {

  private static final long serialVersionUID = 7658975238050745234L;

  @Override
  protected String doIntercept(ActionInvocation invoke) throws Exception {
    System.out.println("invoke...");
    ActionContext ctx = invoke.getInvocationContext();
    Map<String, Object> session = ctx.getSession();
    User user = (User) session.get("user");
    return (user == null) ? "login" : invoke.invoke();
  }
}
