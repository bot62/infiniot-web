package com.infiniot.web.dao;

import java.util.Map;

import com.infiniot.web.model.SensorData;

public interface SensorDataDAO {

  /**
   * Get sensor data, mapped with params.
   * 
   * @param params pamameters
   * @return sensor data array
   */
  public SensorData[] getSensorValues(Map<String, String> params);
}
