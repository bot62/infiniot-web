package com.infiniot.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Class containing information about a sensor device
 *
 * @author mincong-h
 * @author bot62
 */
@Entity
public class Sensor {

  @Id
  @Column(name = "id")
  private String sid; // sensor's id

  private String type; // sensor's type

  private String description; // sensor's description

  @Column(name = "bid")
  private String building; // sensor's installation, building

  @Column(name = "fid")
  private String floor; // sensor's installation, floor

  @Column(name = "rid")
  private String room; // sensor's installation, room

  private String nid; // sensor's node id

  private String name; // sensor's name, e.g. TCA, TCB ...

  private long timestamp; // sensor's value timestamp

  @Column(name = "current_value")
  private double currentValue; // sensor's current value

  private double avg; // sensor's average value in last N seconds

  private double min; // sensor's min value in last N seconds

  private double max; // sensor's max value in last N seconds

  private double speed; // sensor's evalution speed

  @Transient
  public static final String STAT_TYPES[] = {"min", "max", "avg", "speed"};

  public Sensor() {
    timestamp = System.currentTimeMillis();
  }

  public Sensor(String sid, String type) {
    this.sid = sid;
    this.type = type;
  }

  public Sensor(String type, double currentValue) {
    this.type = type;
    this.currentValue = currentValue;
  }

  public Sensor(String sid, String type, double currentValue) {
    this.sid = sid;
    this.type = type;
    this.currentValue = currentValue;
  }

  public Sensor(String sid, String type, String name) {
    this.sid = sid;
    this.type = type;
    this.name = name;
  }

  public Sensor(String sid, String type, double currentValue, String building, String floor,
      String room, String description) {
    this.sid = sid;
    this.type = type;
    this.currentValue = currentValue;
    this.building = building;
    this.floor = floor;
    this.room = room;
    this.description = description;
    this.timestamp = System.currentTimeMillis();
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

  public double getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(double currentValue) {
    this.currentValue = currentValue;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getNid() {
    return nid;
  }

  public void setNid(String nid) {
    this.nid = nid;
  }

  public double getAvg() {
    return avg;
  }

  public void setAvg(double avg) {
    this.avg = avg;
  }

  public double getMin() {
    return min;
  }

  public void setMin(double min) {
    this.min = min;
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Sensor [sid=" + sid + ", type=" + type + ", description=" + description + ", building="
        + building + ", floor=" + floor + ", room=" + room + ", nid=" + nid + ", name=" + name
        + ", timestamp=" + timestamp + ", currentValue=" + currentValue + ", avg=" + avg + ", min="
        + min + ", max=" + max + ", speed=" + speed + "]";
  }
}
