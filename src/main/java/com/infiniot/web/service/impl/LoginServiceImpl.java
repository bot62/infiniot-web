package com.infiniot.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.infiniot.web.dao.UserDAO;
import com.infiniot.web.model.User;
import com.infiniot.web.service.LoginService;

/**
 * Service implementation for handling operations related to authentication. This implementation is
 * realized by the PING32 team. The methods' javadoc have already provided in LoginService
 * interface, so they are not written again for this implementation.
 * 
 * @author mincong-h
 */
public class LoginServiceImpl implements LoginService {

  private UserDAO dao;
  private final Logger log = Logger.getLogger(LoginServiceImpl.class);

  @Override
  public boolean isLoginCorrect(User user) {
    log.debug("Loading user u=" + user);
    log.debug("Loading user reference ...");
    // TODO: change to getUserByEmail
    User ref = dao.getUserByEmail(user.getEmail());
    log.debug("user returned from DAO, ref=" + ref);
    return ref.isPasswordCorrect(user.getPassword());
  }

  /**
   * Get user's full informations
   * 
   * @param email user's email
   * @return user's full informations
   */
  @Override
  public User getUser(String email) {
    return dao.getUserByEmail(email);
  }

  @Override
  public List<User> getUsers() {
    List<User> users = new ArrayList<User>();
    User u1, u2, u3;
    u1 = dao.getUserByUid("1");
    u2 = dao.getUserByUid("2");
    u3 = dao.getUserByUid("3");
    users.add(u1);
    users.add(u2);
    users.add(u3);
    return users;
  }

  public UserDAO getDao() {
    return dao;
  }

  public void setDao(UserDAO dao) {
    this.dao = dao;
  }

}
