package com.infiniot.web.dao;

import java.util.List;
import java.util.Map;

import com.infiniot.web.model.Sensor;

public interface SensorDAO {

  // CRUD for sensor
  public void addSensor(Sensor s);

  public void addSensors(List<Sensor> sensors);

  public void deleteSensor(String sid);

  public void deleteSensors(String[] sids);

  public void deleteSensors(List<Sensor> sensors);

  public Sensor getSensor(String sid);

  public List<Sensor> getSensors();

  public List<Sensor> getSensors(Map<String, String> params);

  public List<Sensor> getSensorsByNid(String nid);

  public void updateSensor(Sensor s);

  public void updateSensors(List<Sensor> sensors);
}
