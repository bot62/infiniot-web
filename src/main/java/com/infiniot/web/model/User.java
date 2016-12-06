package com.infiniot.web.model;

import java.sql.Timestamp;

/**
 * Class containing user's information
 * 
 * @author mincong-h
 */
public class User {

  private int uid;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String role;
  private Timestamp insertDate;

  public User() {}

  public User(int uid, String password, String firstname, String lastname, String email,
      String role, Timestamp insertDate) {
    this.uid = uid;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.role = role;
    this.insertDate = insertDate;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Timestamp getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(Timestamp insertDate) {
    this.insertDate = insertDate;
  }

  public boolean isPasswordCorrect(String password) {
    if (this.password != null && password != null)
      if (this.password.equals(password))
        return true;
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
    result = prime * result + ((insertDate == null) ? 0 : insertDate.hashCode());
    result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    result = prime * result + uid;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (firstname == null) {
      if (other.firstname != null)
        return false;
    } else if (!firstname.equals(other.firstname))
      return false;
    if (insertDate == null) {
      if (other.insertDate != null)
        return false;
    } else if (!insertDate.equals(other.insertDate))
      return false;
    if (lastname == null) {
      if (other.lastname != null)
        return false;
    } else if (!lastname.equals(other.lastname))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (role == null) {
      if (other.role != null)
        return false;
    } else if (!role.equals(other.role))
      return false;
    if (uid != other.uid)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [uid=" + uid + ", password=" + password + ", firstname=" + firstname
        + ", lastname=" + lastname + ", email=" + email + ", role=" + role + ", insertDate="
        + insertDate + "]";
  }
}
