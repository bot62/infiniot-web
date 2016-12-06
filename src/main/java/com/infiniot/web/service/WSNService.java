package com.infiniot.web.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.infiniot.web.model.Node;
import com.infiniot.web.model.Notification;
import com.infiniot.web.model.Sensor;
import com.infiniot.web.model.SensorData;

/**
 * WSN service interface provides the methods for handling all operations related to Wireless Sensor
 * Netword (WSN) in business / logic layer.
 * 
 * @author mincong-h
 */
public interface WSNService {

  /**
   * Add new sensor device.
   * 
   * @param s sensor
   */
  public void addSensor(Sensor s);

  /**
   * Add sensor data to server. If the current mode is FROM_PING32M in server setting, than the
   * request will be accepted. Else, this request will be rejeted by the server.
   * 
   * @param sensorData the sensor data value to add
   * @param nid the target node id
   * @param index the index to add
   */
  public void addSensorData(SensorData sensorData, String nid, int index);

  /**
   * Add a new node device
   * 
   * @param node new node deivce
   */
  public void addNode(Node node);

  /**
   * Get sensor deivce's statistics data using given parameters.
   * 
   * @param nid node id
   * @param sensorName sensor name
   * @return statistics values in map
   */
  public Map<String, Object> getStats(String nid, String sensorName);

  /**
   * Get sensor devices' information by using given parameters.
   * 
   * @param params given parameters
   * @return the list of sensor devices
   */
  public List<Sensor> getSensors(Map<String, String> params);

  /**
   * Get the sensor dataset matched to given parameters
   * 
   * @param params parameters provided
   * @return the list of sensor data
   */
  public List<SensorData> getSensorData(Map<String, String> params);

  /**
   * Get complet list of node devices
   * 
   * @return list of node devices
   */
  public List<Node> getNodes();

  /**
   * Get notification adapted to input parameters.
   * 
   * @param params input parameters
   * @return notification concerned
   */
  public Notification getNotification(Map<String, String> params);

  /**
   * Get Ping32J running mode
   * 
   * @return the running mode
   * @see https://github.com/mincong-h/ping32/wiki/APIs#ping32j-running-mode
   */
  public int getRunningMode();

  /**
   * Calculate the duration in millisecond since the last change of running mode.
   * 
   * @return the different in milisecond
   */
  public long runningSince();

  /**
   * Set Ping32J running mode
   * 
   * @param mode running mode to SET
   * @see https://github.com/mincong-h/ping32/wiki/APIs#ping32j-running-mode
   */
  public void setRunningMode(int mode);

  /**
   * Get the timestamp of last change of running mode.
   * 
   * @return the started timestamp
   */
  public Timestamp startedFrom();

  /**
   * Add statistic values to running node using given params. The statistic results should be
   * received from Data analysis service (Ping32M).
   * 
   * @param params given params
   */
  public void updateStats(Map<String, String> params);
}
