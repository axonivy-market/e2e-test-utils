package com.axonivy.utils.e2etest.enums;

public enum E2EEnvironment {
  MOCK_SERVER("Mock Server Test"), REAL_SERVER("Real Server Test");

  private final String displayName;

  E2EEnvironment(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static E2EEnvironment fromDisplayName(String displayName) {
    for (E2EEnvironment env : values()) {
      if (env.displayName.equalsIgnoreCase(displayName)) {
        return env;
      }
    }
    return MOCK_SERVER;
  }
}
