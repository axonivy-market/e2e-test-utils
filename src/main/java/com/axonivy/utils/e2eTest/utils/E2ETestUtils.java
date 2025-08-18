package com.axonivy.utils.e2eTest.utils;

import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME;

public class E2ETestUtils {
  public static void setUpConfigForContext(String contextName, Runnable setUpConfigForApiTest,
      Runnable setUpConfigForMockServer) {
    switch (contextName) {
      case REAL_CALL_CONTEXT_DISPLAY_NAME:
        setUpConfigForApiTest.run();
        break;
      case MOCK_SERVER_CONTEXT_DISPLAY_NAME:
        setUpConfigForMockServer.run();
        break;
      default:
        break;
    }
  }
}
