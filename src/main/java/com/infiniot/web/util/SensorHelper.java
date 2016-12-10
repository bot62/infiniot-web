package com.infiniot.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infiniot.web.model.Sensor;

/**
 * Sensor helper helps to handle conversion related to model class
 * {@link com.infiniot.web.model.Sensor}.
 * 
 * @author mincong-h
 */
public class SensorHelper {

  /**
   * Cast the list of sensors to map
   * 
   * @param sensors list of sensors
   * @return map of sensors
   */
  public static Map<String, Sensor> asMap(List<Sensor> sensors) {
    Map<String, Sensor> map = new HashMap<>();
    for (Sensor s : sensors) {
      if (s.getSid() == null) {
        throw new NullPointerException("Sensor sid is null");
      }
      if (s.getName() == null) {
        throw new NullPointerException("Sensor name is null");
      }
      map.put(s.getName(), s);
    }
    return map;
  }
}
