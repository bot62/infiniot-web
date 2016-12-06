package com.infiniot.web.dao.hibernate;

import java.util.Map;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.infiniot.web.dao.AbstractDAO;
import com.infiniot.web.dao.SensorDataDAO;
import com.infiniot.web.model.SensorData;
import com.infiniot.web.model.SensorValue0605;

/**
 * Sensor value DAO implementation for experimental sensor values in TEST_0605. This experimental
 * test was done on Friday, 2015/06/05 by the SDIS 76 (Service Départemenral d'Incendie et de
 * Secours de la Seine-Maritime).
 * 
 */
@Transactional
public class Test0605DAOImpl extends AbstractDAO implements SensorDataDAO {

  /**
   * Get a list of experimental sensor data obtained in TEST 0605.
   * 
   * @param params the input parameters
   * @return the list of sensor data
   */
  public SensorData[] getSensorValues(Map<String, String> params) {

    SensorData[] sensorData = new SensorData[25];
    int id = Integer.valueOf(params.get("id"));
    Criteria criteria = getSession().createCriteria(SensorValue0605.class);
    criteria.add(Restrictions.eq("id", id));

    @SuppressWarnings("unchecked")
    List<SensorValue0605> dataset = criteria.list();
    String type = "Temperature";
    SensorValue0605 d = dataset.get(0);
    sensorData[0] = new SensorData("Sensor 01", type, d.getSensor1(), d.getTimestamp());
    sensorData[1] = new SensorData("Sensor 02", type, d.getSensor2(), d.getTimestamp());
    sensorData[2] = new SensorData("Sensor 03", type, d.getSensor3(), d.getTimestamp());
    sensorData[3] = new SensorData("Sensor 04", type, d.getSensor4(), d.getTimestamp());
    sensorData[4] = new SensorData("Sensor 05", type, d.getSensor5(), d.getTimestamp());
    sensorData[5] = new SensorData("Sensor 06", type, d.getSensor6(), d.getTimestamp());
    sensorData[6] = new SensorData("Sensor 07", type, d.getSensor7(), d.getTimestamp());
    sensorData[7] = new SensorData("Sensor 08", type, d.getSensor8(), d.getTimestamp());
    sensorData[8] = new SensorData("Sensor 09", type, d.getSensor9(), d.getTimestamp());
    sensorData[9] = new SensorData("Sensor 10", type, d.getSensor10(), d.getTimestamp());
    sensorData[10] = new SensorData("Sensor 11", type, d.getSensor11(), d.getTimestamp());
    sensorData[11] = new SensorData("Sensor 12", type, d.getSensor12(), d.getTimestamp());
    sensorData[12] = new SensorData("Sensor 13", type, d.getSensor13(), d.getTimestamp());
    sensorData[13] = new SensorData("Sensor 14", type, d.getSensor14(), d.getTimestamp());
    sensorData[14] = new SensorData("Sensor 15", type, d.getSensor15(), d.getTimestamp());
    sensorData[15] = new SensorData("Sensor 16", type, d.getSensor16(), d.getTimestamp());
    sensorData[16] = new SensorData("Sensor 17", type, d.getSensor17(), d.getTimestamp());
    sensorData[17] = new SensorData("Sensor 18", type, d.getSensor18(), d.getTimestamp());
    sensorData[18] = new SensorData("Sensor 19", type, d.getSensor18(), d.getTimestamp());
    sensorData[19] = new SensorData("Sensor 20", type, d.getSensor19(), d.getTimestamp());
    sensorData[20] = new SensorData("Sensor 21", type, d.getSensor20(), d.getTimestamp());
    sensorData[21] = new SensorData("Sensor 22", type, d.getSensor21(), d.getTimestamp());
    sensorData[22] = new SensorData("Sensor 23", type, d.getSensor22(), d.getTimestamp());
    sensorData[23] = new SensorData("Sensor 24", type, d.getSensor23(), d.getTimestamp());
    sensorData[24] = new SensorData("Sensor 25", type, d.getSensor24(), d.getTimestamp());
    return sensorData;
  }
}
