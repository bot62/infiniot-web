package com.infiniot.web.util;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.infiniot.web.model.SensorData;

/**
 * This helper class helps to do complex operations while handling sensor data.
 * 
 * @author mincong-h
 */
public class SensorDataHelper {

  private static final Logger log = Logger.getLogger(SensorDataHelper.class);
  public static String[] EX_SENSOR_IDS =
      {"Example Sensor 1", "Example Sensor 2", "Example sensor 3"};

  public SensorDataHelper() {

  }

  /**
   * Generate a list of random sensor data values according to given timestamp.
   * 
   * @param timestamp the timestamp associated
   * @return the list of sensor data instances
   */
  public static SensorData[] randomDataset(long timestamp) {
    SensorData[] dataset = new SensorData[EX_SENSOR_IDS.length];
    for (int i = 0; i < EX_SENSOR_IDS.length; i++) {
      dataset[i] = generate(EX_SENSOR_IDS[i], timestamp);
    }
    return dataset;
  }

  /**
   * Generate random sensor data value according to given sensor id and given timestamp.
   * 
   * @param sid sensor id associated
   * @param timestamp timestamp associated
   * @return the sensor data instance
   */
  private static SensorData generate(String sid, long timestamp) {
    float v = (float) (Math.random() * 20 + 90);
    SensorData s = new SensorData();
    s.setSid(sid);
    s.setTimestamp(timestamp);
    s.setType("Temperature");
    s.setValue(v);
    return s;
  }

  /**
   * Parse a given JSON object converted from pandas DataFrame, e.g.
   * "{"columns":["TCA","TCB","TCC"],"index":["2016-01-26 21:00:00", "2016-01-26 21:00:01",
   * "2016-01-26 21:00:02"],"data":[[1.2,2.0,3.0], [1.1,2.0,3.0],[1.0,2.0,3.0]]}"
   * 
   * @param json
   * @return
   * @throws ParseException
   * @throws JSONException
   * @see
   */
  public static List<SensorData> parse(String pddf, String sid)
      throws JSONException, ParseException, NullPointerException {

    // check if predicted dataframe is empty
    if ("".equals(pddf)) {
      throw new NullPointerException("DataFrame not found");
    }

    // get params
    JSONObject df = new JSONObject(pddf);
    List<SensorData> predictedData = new LinkedList<SensorData>();
    JSONArray index = df.getJSONArray("index");
    JSONArray data = df.getJSONArray("data");
    int col = -1;
    switch (sid.toLowerCase()) {
      case "tca":
        col = 0;
        break;
      case "tcb":
        col = 1;
        break;
      case "tcc":
        col = 2;
        break;
    }

    // check if there's data in dataframe
    if (index.length() < 1) {
      throw new NullPointerException("DataFrame has no data");
    }

    // append data
    for (int i = 0; i < index.length(); i++) {
      SensorData s = new SensorData();
      s.setSid(sid);
      s.setTimestamp(index.getLong(i));
      s.setType("Temperature");
      s.setValue((float) data.getJSONArray(i).getDouble(col));
      log.debug(i + "," + index.getLong(i) + "," + data.getJSONArray(i).getDouble(col));
      // add to list
      predictedData.add(s);
    }
    log.debug("Predicted data added to " + sid + ". " + predictedData.size() + " points.");
    return predictedData;
  }
}
