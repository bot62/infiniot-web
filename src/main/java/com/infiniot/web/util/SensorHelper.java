package com.infiniot.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infiniot.web.model.Sensor;

/**
 * Sensor helper helps to handle conversion related to model class fr.irseem.model.Sensor
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
      if (s.getName() != null && s.getSid() != null) {
        map.put(s.getName(), s);
      } else {
        throw new NullPointerException("Sensor name is null");
      }
    }
    return map;
  }
}
