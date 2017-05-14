package com.infiniot.web.dao.hibernate;

import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.infiniot.web.dao.AbstractDAO;
import com.infiniot.web.dao.UserDAO;
import com.infiniot.web.model.User;

@Transactional
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

  public User getUser(User user) {
    Criteria criteria = getSession().createCriteria(User.class);
    // TODO: how to handle null case ?
    criteria.add(Restrictions.disjunction().add(Restrictions.eq("id", user.getUid()))
        .add(Restrictions.eq("email", user.getEmail())));
    return (User) criteria.uniqueResult();
  }

  public User getUserByEmail(String email) {
    System.out.println("[UserDAO] getting email=" + email);
    Criteria criteria = getSession().createCriteria(User.class);
    criteria.add(Restrictions.eq("email", email));
    return (User) criteria.uniqueResult();
  }

  public User getUserByUid(String uid) {
    Criteria criteria = getSession().createCriteria(User.class);
    criteria.add(Restrictions.eq("id", 1));
    // TODO: change this part
    System.out.println("[UserDAO] " + criteria.toString());
    User u = (User) criteria.uniqueResult();
    System.out.println("[UserDAO] " + u);
    return u;
    // return new User("1", "123", "Mincong", "Huang", "mincong.h@gmail.com", "chef de projet",
    // null);
  }

  public void updateUser(User user) {
    // TODO: implementation
  }

  public void addUser(User user) {
    // TODO: implementation
  }

  public void deleteUser(User user) {
    // TODO: implementation
  }

  public void deleteUserByEmail(String email) {
    // TODO: implementation
  }

  public void deleteUserByUid(String uid) {
    // TODO: implementation
  }

  @Override
  public List<User> findAll() {
    // TODO: implementation
    return null;
  }

}
