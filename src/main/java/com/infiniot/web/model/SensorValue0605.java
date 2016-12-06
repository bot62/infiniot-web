package com.infiniot.web.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Model for experimental sensor values in TEST_0605. This experimental test was done on Friday,
 * 2015/06/05 by the SDIS 76 (Service Départemenral d'Incendie et de Secours de la Seine-Maritime).
 *
 * @author mincong-h
 */
public class SensorValue0605 {

  public static final long STEP = 500L;
  private int id;
  private float sensor1, sensor2, sensor3, sensor4, sensor5, sensor6, sensor7, sensor8, sensor9,
      sensor10, sensor11, sensor12, sensor13, sensor14, sensor15, sensor16, sensor17, sensor18,
      sensor19, sensor20, sensor21, sensor22, sensor23, sensor24, sensor25;
  private long offset;
  private Timestamp expDate;

  public SensorValue0605() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getOffset() {
    return offset;
  }

  public void setOffset(long offset) {
    this.offset = offset;
  }

  public long getTimestamp() {
    return expDate.getTime() + offset * 1000;
  }

  public float getSensor1() {
    return sensor1;
  }

  public void setSensor1(float sensor1) {
    this.sensor1 = sensor1;
  }

  public float getSensor2() {
    return sensor2;
  }

  public void setSensor2(float sensor2) {
    this.sensor2 = sensor2;
  }

  public float getSensor3() {
    return sensor3;
  }

  public void setSensor3(float sensor3) {
    this.sensor3 = sensor3;
  }

  public float getSensor4() {
    return sensor4;
  }

  public void setSensor4(float sensor4) {
    this.sensor4 = sensor4;
  }

  public float getSensor5() {
    return sensor5;
  }

  public void setSensor5(float sensor5) {
    this.sensor5 = sensor5;
  }

  public float getSensor6() {
    return sensor6;
  }

  public void setSensor6(float sensor6) {
    this.sensor6 = sensor6;
  }

  public float getSensor7() {
    return sensor7;
  }

  public void setSensor7(float sensor7) {
    this.sensor7 = sensor7;
  }

  public float getSensor8() {
    return sensor8;
  }

  public void setSensor8(float sensor8) {
    this.sensor8 = sensor8;
  }

  public float getSensor9() {
    return sensor9;
  }

  public void setSensor9(float sensor9) {
    this.sensor9 = sensor9;
  }

  public float getSensor10() {
    return sensor10;
  }

  public void setSensor10(float sensor10) {
    this.sensor10 = sensor10;
  }

  public float getSensor11() {
    return sensor11;
  }

  public void setSensor11(float sensor11) {
    this.sensor11 = sensor11;
  }

  public float getSensor12() {
    return sensor12;
  }

  public void setSensor12(float sensor12) {
    this.sensor12 = sensor12;
  }

  public float getSensor13() {
    return sensor13;
  }

  public void setSensor13(float sensor13) {
    this.sensor13 = sensor13;
  }

  public float getSensor14() {
    return sensor14;
  }

  public void setSensor14(float sensor14) {
    this.sensor14 = sensor14;
  }

  public float getSensor15() {
    return sensor15;
  }

  public void setSensor15(float sensor15) {
    this.sensor15 = sensor15;
  }

  public float getSensor16() {
    return sensor16;
  }

  public void setSensor16(float sensor16) {
    this.sensor16 = sensor16;
  }

  public float getSensor17() {
    return sensor17;
  }

  public void setSensor17(float sensor17) {
    this.sensor17 = sensor17;
  }

  public float getSensor18() {
    return sensor18;
  }

  public void setSensor18(float sensor18) {
    this.sensor18 = sensor18;
  }

  public float getSensor19() {
    return sensor19;
  }

  public void setSensor19(float sensor19) {
    this.sensor19 = sensor19;
  }

  public float getSensor20() {
    return sensor20;
  }

  public void setSensor20(float sensor20) {
    this.sensor20 = sensor20;
  }

  public float getSensor21() {
    return sensor21;
  }

  public void setSensor21(float sensor21) {
    this.sensor21 = sensor21;
  }

  public float getSensor22() {
    return sensor22;
  }

  public void setSensor22(float sensor22) {
    this.sensor22 = sensor22;
  }

  public float getSensor23() {
    return sensor23;
  }

  public void setSensor23(float sensor23) {
    this.sensor23 = sensor23;
  }

  public float getSensor24() {
    return sensor24;
  }

  public void setSensor24(float sensor24) {
    this.sensor24 = sensor24;
  }

  public float getSensor25() {
    return sensor25;
  }

  public void setSensor25(float sensor25) {
    this.sensor25 = sensor25;
  }

  public Timestamp getExpDate() {
    return expDate;
  }

  public void setExpDate(Timestamp expDate) {
    this.expDate = expDate;
  }

  public List<SensorData> getSensorData() {

    List<SensorData> sensorData = new LinkedList<>();
    long t = getTimestamp();
    sensorData.add(new SensorData("1", "Temperature", sensor1, t));
    sensorData.add(new SensorData("2", "Temperature", sensor2, t));
    sensorData.add(new SensorData("3", "Temperature", sensor3, t));
    sensorData.add(new SensorData("4", "Temperature", sensor4, t));
    sensorData.add(new SensorData("5", "Temperature", sensor5, t));
    sensorData.add(new SensorData("6", "Temperature", sensor6, t));
    sensorData.add(new SensorData("7", "Temperature", sensor7, t));
    sensorData.add(new SensorData("8", "Temperature", sensor8, t));
    sensorData.add(new SensorData("9", "Temperature", sensor9, t));
    sensorData.add(new SensorData("10", "Temperature", sensor10, t));
    sensorData.add(new SensorData("11", "Temperature", sensor11, t));
    sensorData.add(new SensorData("12", "Temperature", sensor12, t));
    sensorData.add(new SensorData("13", "Temperature", sensor13, t));
    sensorData.add(new SensorData("14", "Temperature", sensor14, t));
    sensorData.add(new SensorData("15", "Temperature", sensor15, t));
    sensorData.add(new SensorData("16", "Temperature", sensor16, t));
    sensorData.add(new SensorData("17", "Temperature", sensor17, t));
    sensorData.add(new SensorData("18", "Temperature", sensor18, t));
    sensorData.add(new SensorData("19", "Temperature", sensor19, t));
    sensorData.add(new SensorData("20", "Temperature", sensor20, t));
    sensorData.add(new SensorData("21", "Temperature", sensor21, t));
    sensorData.add(new SensorData("22", "Temperature", sensor22, t));
    sensorData.add(new SensorData("23", "Temperature", sensor23, t));
    sensorData.add(new SensorData("24", "Temperature", sensor24, t));
    sensorData.add(new SensorData("25", "Temperature", sensor25, t));
    return sensorData;
  }

  @Override
  public String toString() {

    return "NiSensorData [id=" + id + ", sensor1=" + sensor1 + ", sensor2=" + sensor2 + ", sensor3="
        + sensor3 + ", sensor4=" + sensor4 + ", sensor5=" + sensor5 + ", sensor6=" + sensor6
        + ", sensor7=" + sensor7 + ", sensor8=" + sensor8 + ", sensor9=" + sensor9 + ", sensor10="
        + sensor10 + ", sensor11=" + sensor11 + ", sensor12=" + sensor12 + ", sensor13=" + sensor13
        + ", sensor14=" + sensor14 + ", sensor15=" + sensor15 + ", sensor16=" + sensor16
        + ", sensor17=" + sensor17 + ", sensor18=" + sensor18 + ", sensor19=" + sensor19
        + ", sensor20=" + sensor20 + ", sensor21=" + sensor21 + ", sensor22=" + sensor22
        + ", sensor23=" + sensor23 + ", sensor24=" + sensor24 + ", sensor25=" + sensor25
        + ", offset=" + offset + "]";
  }
}
