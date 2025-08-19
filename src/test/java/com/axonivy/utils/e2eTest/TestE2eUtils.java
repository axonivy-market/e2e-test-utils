package com.axonivy.utils.e2etest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.axonivy.utils.e2etest.enums.E2EEnvironment.REAL_SERVER;
import static com.axonivy.utils.e2etest.enums.E2EEnvironment.MOCK_SERVER;

import org.junit.jupiter.api.Test;
import com.axonivy.utils.e2etest.utils.E2ETestUtils;

public class TestE2eUtils {

  @Test
  void shouldRunApiConfigWhenRealContext() {
    final boolean[] apiRun = {false};
    final boolean[] mockRun = {false};

    Runnable apiRunnable = () -> apiRun[0] = true;
    Runnable mockRunnable = () -> mockRun[0] = true;

    E2ETestUtils.determineConfigForContext(REAL_SERVER.getDisplayName(), apiRunnable, mockRunnable);

    assertTrue(apiRun[0], "API runnable should run for REAL context");
    assertFalse(mockRun[0], "Mock runnable should NOT run for REAL context");
  }

  @Test
  void shouldRunMockConfigWhenMockContext() {
    final boolean[] apiRun = {false};
    final boolean[] mockRun = {false};

    Runnable apiRunnable = () -> apiRun[0] = true;
    Runnable mockRunnable = () -> mockRun[0] = true;

    E2ETestUtils.determineConfigForContext(MOCK_SERVER.getDisplayName(), apiRunnable, mockRunnable);

    assertTrue(mockRun[0], "Mock runnable should run for MOCK context");
    assertFalse(apiRun[0], "API runnable should NOT run for MOCK context");
  }

  @Test
  void shouldDoNothingWhenUnknownContext() {
    final boolean[] apiRun = {false};
    final boolean[] mockRun = {false};

    Runnable apiRunnable = () -> apiRun[0] = true;
    Runnable mockRunnable = () -> mockRun[0] = true;

    E2ETestUtils.determineConfigForContext("UNKNOWN", apiRunnable, mockRunnable);

    assertFalse(apiRun[0], "API runnable should NOT run for unknown context");
    assertTrue(mockRun[0], "Mock runnable should run for unknown context");
  }
}
