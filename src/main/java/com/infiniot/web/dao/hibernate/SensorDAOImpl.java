package com.infiniot.web.dao.hibernate;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.infiniot.web.dao.AbstractDAO;
import com.infiniot.web.dao.SensorDAO;
import com.infiniot.web.model.Sensor;

@Transactional
public class SensorDAOImpl extends AbstractDAO<Sensor> implements SensorDAO {

  private final Logger log = Logger.getLogger(SensorDAOImpl.class);
  private final String DEL_BY_ID = "DELETE FROM sensors WHERE id = :id";
  private final String DEL_ALL = "DELETE FROM sensors";

  public void addSensor(Sensor s) {
    persist(s);
  }

  public void addSensors(List<Sensor> sensors) {

  }

  public void deleteSensor(String sid) {
    Query query = getSession().createSQLQuery(DEL_BY_ID);
    query.setString("id", sid);
    query.executeUpdate();
  }

  public void deleteSensors(String[] sids) {

  }

  public void deleteSensors(List<Sensor> sensors) {
    Query query = getSession().createSQLQuery(DEL_ALL);
    query.executeUpdate();
  }

  @Override
  public Sensor getSensor(String sid) {
    return null;
  }

  @Override
  public List<Sensor> getSensors() {
    return null;
  }

  @Override
  public Sensor find(String sensorId) {
    Criteria criteria = getSession().createCriteria(Sensor.class);
    criteria.add(Restrictions.eq("id", sensorId));
    log.debug(criteria.toString());
    log.info(((Sensor) criteria.uniqueResult()).toString());
    return (Sensor) criteria.uniqueResult();
  }

  public List<Sensor> findAll() {
    return super.findAll(Sensor.class);
  }

  @SuppressWarnings("unchecked")
  public List<Sensor> getSensors(Map<String, String> params) {
    String nid = params.get("nid");
    Criteria criteria = getSession().createCriteria(Sensor.class);
    return criteria.add(Restrictions.eq("nid", nid)).list();
  }

  @SuppressWarnings("unchecked")
  public List<Sensor> getSensorsByNid(String nid) {
    Query query = getSession().createQuery("FROM Sensor WHERE nid = :nid ");
    query.setParameter("nid", nid);
    return query.list();
  }

  public void updateSensor(Sensor s) {
    getSession().update(s);
  }

  public void updateSensors(List<Sensor> sensors) {

  }

}
