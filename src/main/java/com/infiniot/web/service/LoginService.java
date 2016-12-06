package com.infiniot.web.service;

import java.util.List;

import com.infiniot.web.model.User;

/**
 * Service interface for handling operations related to authentication.
 * 
 * @author mincong-h
 */
public interface LoginService {

  /**
   * Check whether the login is correct
   * 
   * @param user user
   * @return result result checked
   */
  public boolean isLoginCorrect(User user);

  /**
   * Get user's full informations
   * 
   * @param email user's email
   * @return user full informations
   */
  public User getUser(String email);

  public List<User> getUsers();
}
