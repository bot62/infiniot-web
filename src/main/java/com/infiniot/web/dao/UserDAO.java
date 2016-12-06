package com.infiniot.web.dao;

import com.infiniot.web.model.User;

public interface UserDAO {

  // SELECT
  public User getUser(User user);

  public User getUserByEmail(String email);

  public User getUserByUid(String uid);

  // UPDATE
  public void updateUser(User user);

  // CREATE
  public void addUser(User user);

  // DELETE
  public void deleteUser(User user);

  public void deleteUserByEmail(String email);

  public void deleteUserByUid(String uid);
}
