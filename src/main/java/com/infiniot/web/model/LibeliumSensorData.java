package com.infiniot.web.model;

import java.util.HashMap;

public class LibeliumSensorData extends SensorData {

  // Sensor manufacturer Libelium's sensorParser table (ASCII)
  // http://www.libelium.com/uploads/2013/02/data_frame_guide.pdf
  public static final HashMap<String, String> sensorParser = new HashMap<String, String>() {
    private static final long serialVersionUID = -4971517071782430185L;
    {
      put("CO", "Carbon Monoxide");
      put("CO2", "Carbon Dioxide");
      put("TCA", "Temperature Celsius");
      put("TCF", "Temperature Fahrenheit");
      put("HUMA", "Humidity");
      put("STR", "String");
      put("BAT", "Battery");
    }
  };
}
