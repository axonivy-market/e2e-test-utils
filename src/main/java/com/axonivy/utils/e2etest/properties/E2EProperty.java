package com.axonivy.utils.e2etest.properties;

public class E2EProperty {
  public static final String KEY = "testEnvironment";
  public static final String DEFAULT_VALUE = "E2E";

  public static String getTestEnvironmentValue() {
    return System.getProperty(KEY);
  }

  public static boolean isE2EEnable() {
    return DEFAULT_VALUE.equals(getTestEnvironmentValue());
  }
}
