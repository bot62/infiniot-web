package com.infiniot.web.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.infiniot.web.model.SensorData;

/**
 * Test {@link SensorDataHelper}.
 *
 * @author Mincong Huang
 */
public class SensorDataHelperTest {

  @Test
  public void testRandomDataset() {

    long timestamp = System.currentTimeMillis();
    SensorData[] randomDS = SensorDataHelper.randomDataset(timestamp);

    assertEquals("3 temperature sensors: [TCA, TCB, TCC]", 3, randomDS.length);
    for (int i = 0; i < randomDS.length; i++) {
      assertEquals(timestamp, randomDS[i].getTimestamp());
      assertEquals(SensorDataHelper.EX_SENSOR_IDS[i], randomDS[i].getSid());
    }
  }
}
