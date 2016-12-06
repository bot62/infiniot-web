package com.infiniot.web.model;

import java.sql.Timestamp;

/**
 * Class containing information about a node device
 * 
 * @author mincong-h
 */
public class Node {

  private String nid;
  private String type;
  private String description;
  private String building;
  private String floor;
  private String room;
  private float battery;
  private Timestamp insertDate;
  private Timestamp updateDate;
  private double longitude;
  private double latitude;

  public Node() {

  }

  public Node(String nid) {
    this.nid = nid;
  }

  public Node(String nid, String type, double currentValue, String building, String floor,
      String room, String description, float battery, Timestamp insert, Timestamp update) {
    this.nid = nid;
    this.type = type;
    this.description = description;
    this.building = building;
    this.floor = floor;
    this.room = room;
    this.battery = battery;
    this.insertDate = insert;
    this.updateDate = update;
  }

  public String getNid() {
    return nid;
  }

  public void setNid(String nid) {
    this.nid = nid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public Float getBattery() {
    return battery;
  }

  public void setBattery(float battery) {
    this.battery = battery;
  }

  public Timestamp getInsertDate() {
    return this.insertDate;
  }

  public void setInsertDate(Timestamp insert) {
    this.insertDate = insert;
  }

  public Timestamp getUpdateDate() {
    return this.updateDate;
  }

  public void setUpdateDate(Timestamp update) {
    this.updateDate = update;
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

  @Override
  public String toString() {
    return "Node [nid=" + nid + ", type=" + type + ", description=" + description + ", building="
        + building + ", floor=" + floor + ", room=" + room + ", battery=" + battery
        + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", longitude=" + longitude
        + ", latitude=" + latitude + "]";
  }
}
