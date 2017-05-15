package com.infiniot.web.dao.hibernate;

import static org.assertj.core.api.Assertions.assertThat;

import com.infiniot.web.dao.SensorDAO;
import com.infiniot.web.model.Sensor;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author bot62
 */
public class SensorDAOImplTest {

  private static EntityManagerFactory emf;

  private EntityManager em;

  private SensorDAO sensorDAO;

  private Sensor s1;

  private Sensor s2;

  private Sensor s3;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    emf = Persistence.createEntityManagerFactory(SensorDAOImplTest.class.getSimpleName() + "PU");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    if (emf != null) {
      emf.close();
    }
  }

  @Before
  public void setUp() throws Exception {
    s1 = new Sensor("sensor-1", "type-a");
    s2 = new Sensor("sensor-2", "type-b");
    s3 = new Sensor("sensor-3", "type-c");

    em = emf.createEntityManager();
    em.getTransaction().begin();
    Arrays.asList(s1, s2, s3).forEach(em::persist);
    em.getTransaction().commit();

    List<Sensor> result = em.createQuery("select s from Sensor s", Sensor.class).getResultList();
    assertThat(result).containsExactly(s1, s2, s3);

    sensorDAO = new SensorDAOImpl(em);
  }

  @After
  public void tearDown() throws Exception {
    em.getTransaction().begin();
    em.createQuery("delete from Sensor s").executeUpdate();
    em.getTransaction().commit();
    em.close();
  }

  @Test
  public void addSensor() throws Exception {
    Sensor newSensor = new Sensor("sensor-4", "type-4");

    em.getTransaction().begin();
    sensorDAO.addSensor(newSensor);
    em.getTransaction().commit();

    assertThat(sensorDAO.getSensors()).containsExactly(s1, s2, s3, newSensor);
  }

  @Test
  public void addSensors() throws Exception {
    Sensor s4 = new Sensor("sensor-4", "type-4");
    Sensor s5 = new Sensor("sensor-5", "type-5");

    em.getTransaction().begin();
    sensorDAO.addSensors(Arrays.asList(s4, s5));
    em.getTransaction().commit();

    assertThat(sensorDAO.getSensors()).containsExactly(s1, s2, s3, s4, s5);
  }

  @Test
  public void deleteSensor() throws Exception {
  }

  @Test
  public void deleteSensors() throws Exception {
  }

  @Test
  public void deleteSensors1() throws Exception {
  }

  @Test
  public void getSensor() throws Exception {
  }

  @Test
  public void getSensors() throws Exception {
  }

  @Test
  public void find() throws Exception {
  }

  @Test
  public void findAll() throws Exception {
  }

  @Test
  public void getSensors1() throws Exception {
  }

  @Test
  public void getSensorsByNid() throws Exception {
  }

  @Test
  public void updateSensor() throws Exception {
  }

  @Test
  public void updateSensors() throws Exception {
  }

}
