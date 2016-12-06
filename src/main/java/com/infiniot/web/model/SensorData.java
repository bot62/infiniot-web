package com.infiniot.web.model;

import java.sql.Timestamp;

/**
 * Class containing information about a sensor device. This class is different from sensor class, it
 * contains only the value (point) of a sensor. It should be used when using time serie analysis.
 * 
 * @author mincong-h
 */
public class SensorData {

  private String sid; // sensor's id
  private String type; // sensor's type
  private float value; // sensor's value
  private long timestamp; // sensor's value timestamp

  public SensorData() {}

  public SensorData(String type, float value) {
    this.type = type;
    this.value = value;
  }

  public SensorData(String sid, String type, float value) {
    this.sid = sid;
    this.type = type;
    this.value = value;
    this.timestamp = System.currentTimeMillis();
  }

  public SensorData(String sid, String type, float value, long timestamp) {
    this.sid = sid;
    this.type = type;
    this.value = value;
    this.timestamp = timestamp;
  }

  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    Timestamp t = new Timestamp(timestamp);
    return "SensorData [sid=" + sid + ", type=" + type + ", value=" + value + ", timestamp="
        + timestamp + "(" + t + ")]";
  }

}
