package com.infiniot.web.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.infiniot.web.dao.NodeDAO;
import com.infiniot.web.dao.SensorDAO;
import com.infiniot.web.dao.SensorDataDAO;
import com.infiniot.web.dao.UserDAO;
import com.infiniot.web.model.Mode;
import com.infiniot.web.model.Node;
import com.infiniot.web.model.Notification;
import com.infiniot.web.model.RunningNode;
import com.infiniot.web.model.Sensor;
import com.infiniot.web.model.SensorData;
import com.infiniot.web.model.SensorValue0605;
import com.infiniot.web.service.WSNService;
import com.infiniot.web.util.SensorDataHelper;
import com.infiniot.web.util.SensorHelper;

/**
 * WSN service implementation provides the methods for handling all operations related to Wireless
 * Sensor Netword (WSN) in business / logic layer. This implementation is realized by the PING32
 * team. The methods' java doc have already provided in WSNService interface, so they are not
 * written again for this implementation.
 * 
 * @author mincong-h
 */
public class WSNServiceImpl implements WSNService {

  private final String NID_TEST = "SDIS_N_001";
  private final Logger log = Logger.getLogger(WSNServiceImpl.class);
  private SensorDAO sensorDAO;
  private SensorDataDAO test0605DAO;
  private UserDAO userDAO;
  private NodeDAO nodeDAO;
  private Map<String, RunningNode> nodes;
  private int mode = Mode.OFF;
  private Timestamp startedFrom = new Timestamp(System.currentTimeMillis());

  public WSNServiceImpl() {}

  @Override
  public List<Node> getNodes() {
    switch (mode) {
      case Mode.OFF: {
        return nodeDAO.getNodes();
      }
      case Mode.FROM_PING32M: {
        return nodeDAO.getNodes("ESIG_N_%");
      }
      case Mode.FIRE_RANDOM:
      case Mode.FIRE_TEST_1:
      case Mode.FIRE_TEST_2: {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(nodeDAO.getNode(NID_TEST));
        return nodes;
      }
      default: {
        return null;
      }
    }
  }

  @Override
  public Map<String, Object> getStats(String nid, String sensorName) {
    Map<String, Object> map = new HashMap<>();
    switch (mode) {
      case Mode.FROM_PING32M: {
        map = this.nodes.get(nid).getStats(sensorName);
        break;
      }
      default: {
        break;
      }
    }
    return map;
  }

  @Override
  public List<Sensor> getSensors(Map<String, String> params) {
    switch (mode) {
      case Mode.FROM_PING32M: {
        Map<String, Sensor> map = new HashMap<String, Sensor>();
        String nid = params.get("nid");
        // look for node using node id
        // find the map of running sensors
        // return as a list
        map = this.nodes.get(nid).getRunningSensors();
        return new ArrayList<Sensor>(map.values());
      }
      default: {
        return sensorDAO.getSensors(params);
      }
    }
  }

  @Override
  public List<SensorData> getSensorData(Map<String, String> params) {
    String nid = params.get("nid");
    // String sid = params.get("sid");
    int id = (int) (runningSince() / SensorValue0605.STEP);
    params.put("id", String.valueOf(id));
    SensorData[] data = new SensorData[0];
    // sensor data implementation depends on running mode
    switch (mode) {
      case Mode.OFF: {
        data = new SensorData[0];
        break;
      }
      case Mode.FIRE_RANDOM: {
        long t = System.currentTimeMillis();
        data = SensorDataHelper.randomDataset(t);
        break;
      }
      case Mode.FIRE_TEST_1: {
        // get sensor values
        data = test0605DAO.getSensorValues(params);
        this.nodes.get(NID_TEST).add(data);
        break;
      }
      case Mode.FIRE_TEST_2: {
        // TODO: fire test 2 need to be implemented
        data = new SensorData[0];
        break;
      }
      case Mode.FROM_PING32M: {
        data = this.nodes.get(nid).lastDataset();
        break;
      }
      default: {
        break;
      }
    }
    return Arrays.asList(data);
  }

  @Override
  public void addSensor(Sensor s) {
    sensorDAO.addSensor(s);
  }

  @Override
  public void addSensorData(SensorData sensorData, String nid, int index) {
    switch (mode) {
      case Mode.FROM_PING32M: {
        // look up the correct node device using nid
        this.nodes.get(nid).add(sensorData, index);
        break;
      }
      case Mode.OFF:
      case Mode.FIRE_RANDOM:
      case Mode.FIRE_TEST_1:
      case Mode.FIRE_TEST_2:
      default: {
        break;
      }
    }
  }

  @Override
  public void addNode(Node node) {
    nodeDAO.addNode(node);
  }

  @Override
  public void updateStats(Map<String, String> params) {

    switch (mode) {
      case Mode.FROM_PING32M: {
        String nid = params.get("nid");
        String sid = params.get("sid").toUpperCase();
        long timestamp = Long.valueOf(params.get("timestamp"));
        double avg = Double.valueOf(params.get("avg"));
        double min = Double.valueOf(params.get("min"));
        double max = Double.valueOf(params.get("max"));
        double speed = Double.valueOf(params.get("speed"));
        RunningNode runningNode = this.nodes.get(nid);
        runningNode.setAvg(avg, sid);
        runningNode.setMin(min, sid);
        runningNode.setMax(max, sid);
        runningNode.setLastUpdate(timestamp, sid);
        if (speed != 99999) {
          runningNode.setSpeed(speed, sid);
        }
        break;
      }
      default: {
        String message = String.format("Update statistics updateStats() request rejeted, "
            + "because the server is running under mode N° %d."
            + "Change to mode=4 (FROM_PING32M) to enable reception.", mode);
        log.debug(message);
        break;
      }
    }
  }

  @Override
  public Notification getNotification(Map<String, String> params) {

    Notification notification = null;
    Node node = nodeDAO.getNode(NID_TEST);
    switch (mode) {
      case Mode.FIRE_RANDOM: {
        long t = System.currentTimeMillis();
        RunningNode runningNode = new RunningNode();
        runningNode.setNode(node);
        runningNode.setLastData(SensorDataHelper.randomDataset(t));
        float results[] = runningNode.statsForNode();
        notification = getNotification(results, node);
        break;
      }
      case Mode.FIRE_TEST_1: {
        float[] results = this.nodes.get(NID_TEST).statsForNode();
        notification = getNotification(results, node);
        break;
      }
      case Mode.FROM_PING32M: {
        // TODO: The algorithm is bad and should be changed by somebody.
        // It should only be used in the demonstration.
        //
        // get notification from each running node, then we compare the
        // severity using notification level. The first notification of
        // the highest level will be returned.
        List<Notification> notifications = new ArrayList<>();
        for (Entry<String, RunningNode> entry : this.nodes.entrySet()) {
          notification = entry.getValue().getNotification();
          notifications.add(notification);
        }
        if (notifications.size() > 0) {
          for (Notification nt : notifications) {
            // If we have fire, return it
            if (nt.getLevel().equals(Notification.LEVEL_HIGH)) {
              log.info("notification=" + nt);
              return nt;
              // If we have a warning, we should replace the previous
              // notification obtained by this one. If there's no
              // provious notification, we give a notification as well.
            } else if (nt.getLevel().equals(Notification.LEVEL_MEDIUM) || notification == null) {
              notification = nt;
            }
          }
        } else {
          notification = new Notification();
          notification.setContent("Not enough data for the notification");
        }
        break;
      }
      case Mode.FIRE_TEST_2:
      case Mode.OFF: {
        // In OFF mode, the web-server PING32J generates random
        // notification of LEVEL-1, so that Android can test the
        // wsn/getNotification API.
        double a = Math.random() * 10 + 15;
        double b = Math.random() * 10 + 15;
        float[] results = {(float) (Math.min(a, b)), (float) (Math.max(a, b)), (float) (a + b) / 2};
        notification = getNotification(results, node);
        break;
      }
    }
    log.debug("notification=" + notification);
    return notification;
  }

  public SensorDAO getSensorDAO() {
    return sensorDAO;
  }

  public void setSensorDAO(SensorDAO sensorDAO) {
    this.sensorDAO = sensorDAO;
  }

  public SensorDataDAO getTest0605DAO() {
    return test0605DAO;
  }

  public void setTest0605DAO(SensorDataDAO test0605DAO) {
    this.test0605DAO = test0605DAO;
  }

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public NodeDAO getNodeDAO() {
    return nodeDAO;
  }

  public void setNodeDAO(NodeDAO nodeDAO) {
    this.nodeDAO = nodeDAO;
  }

  public void setRunningMode(int mode) {
    if (this.mode != mode) {
      this.mode = mode;
      // reset started time
      startedFrom = new Timestamp(System.currentTimeMillis());
      // reset node values
      this.nodes = new HashMap<>();
      switch (mode) {
        case Mode.FROM_PING32M: {
          List<Sensor> sensors = new ArrayList<>();
          for (Node node : getNodes()) {
            // init running node
            RunningNode rn = new RunningNode();
            rn.setSensorSize(3);
            rn.setNode(node);
            // get sensors from DAO
            sensors = sensorDAO.getSensorsByNid(node.getNid());
            rn.setRunningSensors(SensorHelper.asMap(sensors));
            // set result to the list of running nodes
            nodes.put(node.getNid(), rn);
          }
          break;
        }
        case Mode.FIRE_RANDOM: {
          Node n = nodeDAO.getNode("ESIG_N_001");
          RunningNode rn = new RunningNode();
          rn.setNode(n);
          this.nodes.put("ESIG_N_001", rn);
          break;
        }
        case Mode.FIRE_TEST_1:
        case Mode.FIRE_TEST_2: {
          Node n = nodeDAO.getNode(NID_TEST);
          RunningNode rn = new RunningNode();
          rn.setNode(n);
          this.nodes.put(NID_TEST, rn);
          break;
        }
        default: {
          break;
        }
      }
    }
  }

  public int getRunningMode() {
    return this.mode;
  }

  public Timestamp startedFrom() {
    return startedFrom;
  }

  public long runningSince() {
    long now = System.currentTimeMillis();
    return now - startedFrom.getTime();
  }

  private Notification getNotification(float[] results, Node node) {
    Notification notification = new Notification();
    notification.setTMin(results[0]);
    notification.setTMax(results[1]);
    notification.setTAvg(results[2]);
    notification.setLevel();
    notification.setContent();
    notification.setLocation(node.getBuilding());
    notification.setLongitude(node.getLongitude());
    notification.setLatitude(node.getLatitude());
    return notification;
  }
}
