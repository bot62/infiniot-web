package com.infiniot.web.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * This model contains the informations of an IoT node device's informations, the sensor data that
 * it collected and the furture values computed by ARMA process in python (PING32M).
 *
 * @author mincong-h
 */
public class RunningNode {

  private final Logger log = Logger.getLogger(RunningNode.class);
  private Node node;
  private float avg, min, max;
  private long lastUpdate;
  // TODO: change to linkedlist
  private SensorData[] lastData;
  private Map<String, Sensor> runningSensors;

  public RunningNode() {
    lastUpdate = System.currentTimeMillis();
    runningSensors = new HashMap<>();
    // TODO: only for test
    min = 99999;
    max = 0;
  }

  public void add(SensorData[] dataset) {
    lastData = dataset;
  }

  public void add(SensorData s, int index) {
    lastData[index] = s;
  }

  /**
   * Initialize sensor array by given array size.
   * 
   * @param size the number of sensor in node, e.g. 3 for waspmote
   */
  public void setSensorSize(int size) {
    lastData = new SensorData[size];
  }

  public Node getNode() {
    return node;
  }

  public String getNodeId() {
    return this.node.getNid();
  }
  /*
   * public List<SensorData[]> getDatasets() { return datasets; }
   * 
   * public SensorData[] getDataset(int index) { return datasets.get(index); }
   */

  /**
   * Compute statistic values of a given sensor
   * 
   * @param sensorName sensor name, e.g. "TCA", "TCB"
   * @return map of statistics values
   */
  public Map<String, Object> getStats(String sensorName) {
    Map<String, Object> maps = new HashMap<>();
    Sensor s = runningSensors.get(sensorName);
    maps.put("avg", s.getAvg());
    maps.put("min", s.getMin());
    maps.put("max", s.getMax());
    maps.put("speed", s.getSpeed());
    maps.put("timestamp", s.getTimestamp());
    return maps;
  }

  public float getAvg() {
    return avg;
  }

  public float getMin() {
    return min;
  }

  public float getMax() {
    return max;
  }

  public long getLastUpdate() {
    return lastUpdate;
  }

  public Notification getNotification() {
    Notification n = new Notification();
    // if there's any running sensor in running node,
    // then iterate them to find the min, max, avg
    if (!runningSensors.isEmpty()) {
      log.debug("comparing sensors' min / max for notification");
      for (Map.Entry<String, Sensor> sensor : runningSensors.entrySet()) {
        log.debug("sid=" + sensor.getValue().getSid() + ", min=" + sensor.getValue().getMin()
            + ", max=" + sensor.getValue().getMax() + ", avg=" + sensor.getValue().getAvg());
        min = Math.min(min, (float) sensor.getValue().getMin());
        max = Math.max(max, (float) sensor.getValue().getMax());
        avg = (avg + (float) sensor.getValue().getAvg()) / 2;
      }
    }
    n.setTMin(min);
    n.setTMax(max);
    n.setTAvg(avg);
    n.setLevel();
    n.setContent();
    n.setLocation(node.getBuilding());
    n.setLongitude(node.getLongitude());
    n.setLatitude(node.getLatitude());
    log.debug("notification=" + n);
    return n;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public void setLastData(SensorData[] dataset) {
    this.lastData = dataset;
  }

  public void setAvg(double avg, String sid) {
    runningSensors.get(sid).setAvg(avg);
    this.avg = ((float) avg + this.avg) / 2;
  }

  public void setLastUpdate(long lastUpdate, String sid) {
    this.lastUpdate = lastUpdate;
    runningSensors.get(sid).setTimestamp(lastUpdate);
  }

  public void setMin(double min, String sid) {
    runningSensors.get(sid).setMin(min);
    this.min = Math.min((float) min, this.min);
  }

  public void setMax(double max, String sid) {
    runningSensors.get(sid).setMax(max);
    this.max = Math.max((float) max, this.max);
  }

  public void setSpeed(double speed, String sid) {
    runningSensors.get(sid).setSpeed(speed);
  }

  public SensorData[] lastDataset() {
    return lastData;
  }

  /**
   * Compute the statistic results for the entire node. ONLY for test cases.
   * 
   * @return
   */
  public float[] statsForNode() {

    float[] results = {0, 0, 0};
    float max = lastData[0].getValue();
    float min = lastData[0].getValue();
    float avg = 0;
    float sum = lastData[0].getValue();
    for (SensorData sd : lastData) {
      sum += sd.getValue();
      max = Math.max(sd.getValue(), max);
      min = Math.min(sd.getValue(), min);
    }
    avg = sum / lastData.length;
    results[0] = min;
    results[1] = max;
    results[2] = avg;
    return results;
  }

  public Map<String, Sensor> getRunningSensors() {
    return runningSensors;
  }

  public void setRunningSensors(Map<String, Sensor> runningSensors) {
    this.runningSensors = runningSensors;
  }
}
