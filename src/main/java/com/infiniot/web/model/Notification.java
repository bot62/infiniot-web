package com.infiniot.web.model;

import java.sql.Timestamp;

/**
 * Notifiation for Android API
 * 
 * @author mincong-h
 */
public class Notification {

  // Notification LEVELs
  public static final String LEVEL_LOW = "1";
  public static final String LEVEL_MEDIUM = "2";
  public static final String LEVEL_HIGH = "3";

  // Nofication CRITERIAs
  public static final float WARNING_TCA_AVG = 40.0f;
  public static final float WARNING_TCA_MAX = 60.0f;

  private String id;
  private String level;
  private String location;
  private float tMax;
  private float tMin;
  private float tAvg;
  private double longitude;
  private double latitude;
  private String content;
  private Timestamp timestamp;

  /**
   * Construct a notification with all parameters
   * 
   * @param level notification level
   * @param location notification location (building?)
   * @param content natification content (message)
   * @param timestamp notification created timestamp
   */
  public Notification(String level, String location, String content, Timestamp timestamp) {
    this.id = String.valueOf(System.currentTimeMillis());
    this.level = level;
    this.location = location;
    this.content = content;
    this.timestamp = timestamp;
  }

  public Notification() {
    // set attributes
    this.id = String.valueOf(System.currentTimeMillis());
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public void setLevel() {
    if (this.tMax > WARNING_TCA_MAX && this.tAvg > WARNING_TCA_AVG) {
      this.level = LEVEL_HIGH;
    } else if (tMax < WARNING_TCA_MAX && tAvg < WARNING_TCA_AVG) {
      this.level = LEVEL_LOW;
    } else {
      this.level = LEVEL_MEDIUM;
    }
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public float getTMin() {
    return tMin;
  }

  public void setTMin(float tMin) {
    this.tMin = tMin;
  }

  public float getTMax() {
    return tMax;
  }

  public void setTMax(float tMax) {
    this.tMax = tMax;
  }

  public float getTAvg() {
    return tAvg;
  }

  public void setTAvg(float tAvg) {
    this.tAvg = tAvg;
  }

  @Override
  public String toString() {
    return "Notification [id=" + id + ", level=" + level + ", location=" + location + ", tMax="
        + tMax + ", tMin=" + tMin + ", tAvg=" + tAvg + ", longitude=" + longitude + ", latitude="
        + latitude + ", content=" + content + ", timestamp=" + timestamp + "]";
  }

  /**
   * Provide a content mapping with different security levels
   * 
   */
  public void setContent() {
    switch (this.level) {
      case Notification.LEVEL_LOW:
        this.content = "All sensor checked (daily message).";
        break;
      case Notification.LEVEL_MEDIUM:
        this.content =
            "There's a sensor with high temperature. " + "max = " + String.format("%.2f", this.tMax)
                + "°C, " + "avg = " + String.format("%.2f", this.tAvg) + "°C.";
        break;
      case Notification.LEVEL_HIGH:
        this.content = "There's a fire ! " + "max = " + String.format("%.2f", this.tMax) + "°C, "
            + "avg = " + String.format("%.2f", this.tAvg) + "°C.";
        break;
    }
  }
}
