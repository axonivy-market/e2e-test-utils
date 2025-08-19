package com.axonivy.utils.e2etest.utils;

import com.axonivy.utils.e2etest.enums.E2EEnvironment;

public class E2ETestUtils {
  public static void determineConfigForContext(String contextName, Runnable setUpConfigForApiTest,
      Runnable setUpConfigForMockServer) {
    E2EEnvironment env = E2EEnvironment.fromDisplayName(contextName);
    switch (env) {
      case REAL_SERVER:
        setUpConfigForApiTest.run();
        break;
      case MOCK_SERVER:
        setUpConfigForMockServer.run();
        break;
      default:
        break;
    }
  }
}
