package com.infiniot.web.model;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class containing information about a node device
 *
 * @author mincong-h
 */
@Entity
public class Node {

  @Id
  @Column(name = "id")
  private String nid;

  private String type;

  private String description;

  @Column(name = "bid")
  private String building;

  @Column(name = "fid")
  private String floor;

  @Column(name = "rid")
  private String room;

  private float battery;

  // TODO what does insert date mean?
  private ZonedDateTime insertDate;

  // TODO what does update date mean?
  private ZonedDateTime updateDate;

  private double longitude;

  private double latitude;

  public Node() {

  }

  public Node(String nid) {
    this.nid = nid;
  }

  public Node(String nid, String type, String building, String floor,
      String room, String description, float battery, ZonedDateTime insertDate,
      ZonedDateTime updateDate) {
    this.nid = nid;
    this.type = type;
    this.description = description;
    this.building = building;
    this.floor = floor;
    this.room = room;
    this.battery = battery;
    this.insertDate = insertDate;
    this.updateDate = updateDate;
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

  public ZonedDateTime getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(ZonedDateTime insertDate) {
    this.insertDate = insertDate;
  }

  public ZonedDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(ZonedDateTime updateDate) {
    this.updateDate = updateDate;
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
