package com.infiniot.web.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.infiniot.web.model.Sensor;

/**
 * Test {@link SensorHelper}.
 *
 * @author Mincong Huang
 */
public class SensorHelperTest {

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Test
  public void testAsMap_normal() {

    Sensor s1 = new Sensor("1", "unknown type", "Sensor 1");
    Sensor s2 = new Sensor("2", "unknown type", "Sensor 2");
    Sensor s3 = new Sensor("3", "unknown type", "Sensor 3");
    List<Sensor> sensors = Arrays.asList(s1, s2, s3);
    List<String> names = Arrays.asList("Sensor 1", "Sensor 2", "Sensor 3");

    Map<String, Sensor> map = SensorHelper.asMap(sensors);
    for (Entry<String, Sensor> entry : map.entrySet()) {
      assertTrue(sensors.contains(entry.getValue()));
      assertTrue(names.contains(entry.getKey()));
    }
  }

  @Test
  public void testAsMap_sidNull() {

    expectedEx.expect(NullPointerException.class);
    expectedEx.expectMessage("Sensor sid is null");

    Sensor s = new Sensor(null, "unknow type", "Sensor 1");
    Map<String, Sensor> map = SensorHelper.asMap(Arrays.asList(s));
    fail("NullPointerException expected: the sensor \"s\" has null as sid. Got map=" + map);
  }

  @Test
  public void testAsMap_nameNull() {

    expectedEx.expect(NullPointerException.class);
    expectedEx.expectMessage("Sensor name is null");

    Sensor s = new Sensor("1", "unknow type", null);
    Map<String, Sensor> map = SensorHelper.asMap(Arrays.asList(s));
    fail("NullPointerException expected: the sensor \"s\" has null as name. Got map=" + map);
  }
}
