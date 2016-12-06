package com.infiniot.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.infiniot.web.exception.ExceptionCode;
import com.infiniot.web.exception.ForbiddenException;
import com.infiniot.web.model.Node;
import com.infiniot.web.model.Notification;
import com.infiniot.web.model.Security;
import com.infiniot.web.model.Sensor;
import com.infiniot.web.model.SensorData;
import com.infiniot.web.service.WSNService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * WSN action is the controller for all actions related to Wireless Sensor Network (WSN)
 * 
 * @author mincong-h
 */
public class WSNAction extends ActionSupport {

  private static final long serialVersionUID = 2357942047114240172L;
  private final Logger log = Logger.getLogger(WSNAction.class);
  private WSNService wsnService;
  private final String JSON = "json";
  private final String ADMIN = "admin";

  /**
   * AJAX request for getting sensors' info.
   * 
   * @see https://github.com/mincong-h/ping32/wiki/APIs
   * @return null
   * @throws ManuFacturerNotFoundException
   * @throws IOException
   */
  public String getSensors() throws IOException {

    // set HTTP request and response
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session = request.getSession();
    response.setContentType("text/html;charset=UTF-8");

    // get parameters
    Hashtable<String, String> params = new Hashtable<String, String>();
    String nid = request.getParameter("nid");
    String bid = request.getParameter("bid");
    String sid = request.getParameter("sid");
    String duration = request.getParameter("duration"); // in second
    String key = request.getParameter("key");
    JSONArray json = new JSONArray();

    try {

      // security: access key check
      if (session.getAttribute("user") == null) {
        Security.check(key);
      }

      // put params into hash map
      params.put("nid", nid != null ? nid : "");
      params.put("bid", bid != null ? bid : "");
      params.put("sid", sid != null ? sid.toUpperCase() : "");
      params.put("duration", duration != null ? duration : "1");

      // compute
      List<Sensor> sensors = wsnService.getSensors(params);
      json = new JSONArray(sensors.toArray());

    } catch (SecurityException | NullPointerException e) {

      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json.put(error);

    } finally {
      // send back
      response.getWriter().write(json.toString());
    }
    return null;
  }

  /**
   * AJAX request for getting sensors' data with different parameters
   * 
   * @see https://github.com/mincong-h/ping32/wiki/APIs#wsngetsensordata
   * @return null
   * @throws ManufacturerNotFoundException if manufactuer is null
   * @throws IOException if JSON cannot be written in HTTP response
   */
  public String getSensorData() throws IOException {

    // initialization
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();

    // get parameters
    HashMap<String, String> params = new HashMap<String, String>();
    String nid = request.getParameter("nid");
    String bid = request.getParameter("bid");
    String duration = request.getParameter("duration");
    String key = request.getParameter("key");
    JSONArray json = new JSONArray();

    try {

      // security: access key check
      if (session.getAttribute("user") == null) {
        Security.check(key);
      }

      // put params into hash map
      params.put("nid", nid != null ? nid : "");
      params.put("bid", bid != null ? bid : "");
      params.put("duration", duration != null ? duration : "1");

      // compute
      List<SensorData> sensors = wsnService.getSensorData(params);
      json = new JSONArray(sensors.toArray());

    } catch (SecurityException | NullPointerException e) {

      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json.put(error);

    } finally {
      // send back
      response.getWriter().write(json.toString());
    }
    return null;
  }

  /**
   * AJAX request for getting node devices' information of given parameters.
   * 
   * @return
   * @throws IOException
   */
  public String getNodes() throws IOException {

    HttpServletRequest request = ServletActionContext.getRequest();
    String key = request.getParameter("key");
    JSONArray json = new JSONArray();
    try {
      Security.check(key);
      List<Node> nodes = wsnService.getNodes();
      json = new JSONArray(nodes.toArray());

    } catch (SecurityException e) {
      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json.put(error);

    } finally {
      request.setAttribute(JSON, json);
    }
    return JSON;
  }

  /**
   * AJAX request for getting the current notification of a building.
   * 
   * @return null
   * @throws IOException if JSON cannot be written in HTTP response
   */
  public String getNotification() throws IOException {

    // initialization
    HttpServletRequest request = ServletActionContext.getRequest();

    // get parameters
    HashMap<String, String> params = new HashMap<String, String>();
    String bid = request.getParameter("building");
    String key = request.getParameter("key");
    JSONArray json = new JSONArray();

    try {

      // security: access key check
      Security.check(key);
      // put parameters into hashmap
      if (bid != null)
        params.put("bid", bid);
      // compute
      Notification notification = wsnService.getNotification(params);
      json.put(new JSONObject(notification));

    } catch (SecurityException | NullPointerException e) {

      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json.put(error);

    } finally {
      request.setAttribute(JSON, json);
    }
    return JSON;
  }

  /**
   * Get the statistics results obtained from Python third part library
   * 
   * @return
   * @throws IOException
   * @see https://github.com/mincong-h/ping32/wiki/getStats-API
   */
  public String getStats() throws IOException {

    // initialization
    HttpServletRequest request = ServletActionContext.getRequest();

    String key = request.getParameter("key");
    String nid = request.getParameter("nid");
    String sName = request.getParameter("sid").toUpperCase();
    JSONObject json = new JSONObject();

    try {
      // security: access key check
      Security.check(key);
      // compute
      Map<String, Object> map = wsnService.getStats(nid, sName);
      // return
      json = new JSONObject(map);

    } catch (SecurityException | NullPointerException e) {
      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json = error;

    } finally {
      request.setAttribute(JSON, json);
    }
    return JSON;
  }

  /**
   * Add sensor data set to web server. This API is designed for Python application for sending the
   * data analysis result.
   * 
   * @return null
   * @throws IOException
   * @throws ParseException when string pattern "yyyy-MM-dd HH:mm:ss" is not respected
   */
  public String addSensorDatas() throws IOException, ParseException {

    // initialization
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/html;charset=UTF-8");
    SensorData a, b, c;
    JSONObject json = new JSONObject();

    // get parameters
    String key = request.getParameter("key");

    // get realtime API params
    String nid = request.getParameter("nid");
    float tca = Float.valueOf(request.getParameter("tca"));
    float tcb = Float.valueOf(request.getParameter("tcb"));
    float tcc = Float.valueOf(request.getParameter("tcc"));
    // Long timestamp = Long.valueOf(request.getParameter("timestamp"));
    Date date =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("timestamp"));
    Long timestamp = date.getTime() + 3600 * 1000L; // UTC+1

    try {
      // security: access key check
      Security.check(key);
      // put params into sensor data
      a = new SensorData();
      a.setSid("TCA");
      a.setType("Temperature");
      a.setTimestamp(timestamp);
      a.setValue(tca);
      b = new SensorData();
      b.setSid("TCB");
      b.setType("Temperature");
      b.setTimestamp(timestamp);
      b.setValue(tcb);
      c = new SensorData();
      c.setSid("TCC");
      c.setType("Temperature");
      c.setTimestamp(timestamp);
      c.setValue(tcc);
      // compute
      String msg = String.format("addSensorDatas: (%s, %d, %.1f, %.1f, %.1f)", nid, timestamp, tca,
          tcb, tcc);
      log.info(msg);
      // log.debug(msg);
      wsnService.addSensorData(a, nid, 0);
      wsnService.addSensorData(b, nid, 1);
      wsnService.addSensorData(c, nid, 2);

    } catch (SecurityException | NullPointerException e) {

      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json = error;

    } finally {
      request.setAttribute(JSON, json);
    }
    return JSON;
  }

  /**
   * Add sensor to server.
   * 
   * @return
   * @throws ForbiddenException if user does not login or POST method is used
   */
  public String addSensor() throws ForbiddenException {

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session = request.getSession();
    response.setContentType("text/html;charset=UTF-8");
    Sensor sensor;

    Security.check(session);
    if (!request.getMethod().equals("POST"))
      throw new ForbiddenException("Please use POST method.");

    String sid = request.getParameter("sensor-sid").toUpperCase();
    String bid = request.getParameter("sensor-bid");
    String fid = request.getParameter("sensor-fid");
    String rid = request.getParameter("sensor-rid");
    String type = request.getParameter("sensor-type");
    String description = request.getParameter("sensor-description");

    sensor = new Sensor();
    sensor.setSid(sid);
    sensor.setBuilding(bid);
    sensor.setFloor(fid);
    sensor.setRoom(rid);
    sensor.setType(type);
    sensor.setDescription(description);
    // TODO: delete this part later
    sensor.setCurrentValue(12f);
    wsnService.addSensor(sensor);
    return SUCCESS;
  }

  /**
   * Add node to server.
   * 
   * @return
   * @throws ForbiddenException if user does not login or POST method is used
   */
  public String addNode() throws ForbiddenException {

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session = request.getSession();
    response.setContentType("text/html;charset=UTF-8");
    Node node;

    Security.check(session);
    if (!request.getMethod().equals("POST"))
      throw new ForbiddenException("Please use POST method.");

    String nid = request.getParameter("node-nid");
    String bid = request.getParameter("node-bid");
    String fid = request.getParameter("node-fid");
    String rid = request.getParameter("node-rid");
    String type = request.getParameter("node-type");
    String lng = request.getParameter("node-longitude");
    String lat = request.getParameter("node-latitude");

    node = new Node();
    node.setNid(nid);
    node.setBuilding(bid);
    node.setFloor(fid);
    node.setRoom(rid);
    node.setType(type);
    node.setLongitude(Double.valueOf(lng));
    node.setLatitude(Double.valueOf(lat));
    node.setDescription("");
    node.setBattery(1f);
    wsnService.addNode(node);
    return SUCCESS;
  }

  /**
   * Set running mode of this web application.
   * 
   * @return
   * @throws ForbiddenException
   */
  public String setRunningMode() throws ForbiddenException {

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session = request.getSession();
    response.setContentType("text/html;charset=UTF-8");

    Security.check(session);
    int mode = Integer.valueOf(request.getParameter("mode"));
    wsnService.setRunningMode(mode);
    return ADMIN;
  }

  /**
   * Get running mode of this web application.
   * 
   * @return
   * @throws IOException
   */
  public String getRunningMode() throws IOException {

    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/html;charset=UTF-8");
    String key = request.getParameter("key");
    JSONObject json = new JSONObject();

    try {
      Security.check(key);
      int mode = wsnService.getRunningMode();
      json.put("running-mode", mode);

    } catch (SecurityException | NullPointerException e) {

      JSONObject error = Security.getError(ExceptionCode.SECURITY, e.getMessage());
      json = error;

    } finally {
      request.setAttribute(JSON, json);
    }
    return JSON;
  }

  /**
   * Update the running nodes statistics results obtained from Python third part library
   * StatsModels.
   * 
   * @return
   * @throws ParseException
   * @see https://github.com/mincong-h/ping32/wiki/updateStats-API
   */
  public String updateStats() throws ParseException {
    // initialization
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/html;charset=UTF-8");
    Date date =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("timestamp"));
    Long timestamp = date.getTime() + 3600 * 1000L; // UTC+1
    Map<String, String> stats = new HashMap<String, String>();
    stats.put("nid", request.getParameter("nid"));
    stats.put("sid", request.getParameter("sid").toUpperCase());
    stats.put("timestamp", timestamp.toString());
    for (String key : Sensor.STAT_TYPES) {
      // get parameter name, e.g. "avg"
      String value = request.getParameter(key);
      if (value != null) {
        stats.put(key, value);
      }
    }
    log.info("updateStats: (" + stats.toString() + ")");
    wsnService.updateStats(stats);
    return null;
  }

  /**
   * Getter of WSN service, used by Spring Framework for the dependency injection. (WSN = Wireless
   * Sensor Network)
   * 
   * @return the WSN Service
   */
  public WSNService getWsnService() {
    return wsnService;
  }

  /**
   * Setter of WSN service, used by Spring Framework for the dependency injection. (WSN = Wireless
   * Sensor Network)
   * 
   * @param wsnService WSN Service
   */
  public void setWsnService(WSNService wsnService) {
    this.wsnService = wsnService;
  }
}
