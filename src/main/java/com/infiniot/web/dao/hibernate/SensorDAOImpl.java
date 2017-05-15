package com.infiniot.web.dao.hibernate;

import com.infiniot.web.dao.AbstractDAO;
import com.infiniot.web.dao.SensorDAO;
import com.infiniot.web.model.Sensor;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional
public class SensorDAOImpl extends AbstractDAO<Sensor> implements SensorDAO {

  public SensorDAOImpl() {

  }

  SensorDAOImpl(EntityManager em) {
    super.em = em;
  }

  public void addSensor(Sensor s) {
    persist(s);
  }

  @Override
  public void addSensors(List<Sensor> sensors) {
    sensors.forEach(em::persist);
  }

  @Override
  public void deleteSensor(String sid) {
    em.createQuery("delete from Sensor where id = :id")
        .setParameter("id", sid)
        .executeUpdate();
  }

  @Override
  public void deleteSensors(String[] sids) {
    // TODO
  }

  @Override
  public void deleteSensors(List<Sensor> sensors) {
    sensors.forEach(em::remove);
  }

  @Override
  public Sensor getSensor(String sensorId) {
    return em.find(Sensor.class, sensorId);
  }

  @Override
  public List<Sensor> getSensors() {
    return findAll();
  }

  @Override
  public Sensor find(String sensorId) {
    return em.find(Sensor.class, sensorId);
  }

  public List<Sensor> findAll() {
    return super.findAll(Sensor.class);
  }

  /**
   * @deprecated This is a misleading method, it only use the parameter "nid",
   * the node ID, among all the parameters.
   */
  @Override
  @Deprecated
  @SuppressWarnings("unchecked")
  public List<Sensor> getSensors(Map<String, String> params) {
    String nid = params.get("nid");
    Criteria criteria = getSession().createCriteria(Sensor.class);
    return criteria.add(Restrictions.eq("nid", nid)).list();
  }

  @Override
  public List<Sensor> getSensorsByNid(String nid) {
    return em.createQuery("FROM Sensor WHERE nid = :nid", Sensor.class)
        .setParameter("nid", nid)
        .getResultList();
  }

  @Override
  public void updateSensor(Sensor s) {
    em.merge(s);
  }

  @Override
  public void updateSensors(List<Sensor> sensors) {
    sensors.forEach(this::update);
  }

}
