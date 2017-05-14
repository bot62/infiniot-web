package com.infiniot.web.util;

import static org.assertj.core.api.Assertions.assertThat;

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

    assertThat(randomDS).hasSize(3).as("3 temperature sensors: [TCA, TCB, TCC]");
    for (int i = 0; i < randomDS.length; i++) {
      String expectedSID = SensorDataHelper.EX_SENSOR_IDS[i];
      assertThat(randomDS[i].getTimestamp()).isEqualTo(timestamp);
      assertThat(randomDS[i].getSid()).isEqualTo(expectedSID);
    }
  }
}
