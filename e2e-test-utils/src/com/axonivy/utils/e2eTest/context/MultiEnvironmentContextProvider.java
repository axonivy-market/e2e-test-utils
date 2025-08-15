package com.axonivy.utils.e2eTest.context;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import static com.axonivy.utils.e2eTest.constants.E2eTestConstants.END_TO_END_TESTING_ENVIRONMENT_KEY;
import static com.axonivy.utils.e2eTest.constants.E2eTestConstants.END_TO_END_TESTING_ENVIRONMENT_VALUE;
import static com.axonivy.utils.e2eTest.constants.E2eTestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME;
import static com.axonivy.utils.e2eTest.constants.E2eTestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME;

public class MultiEnvironmentContextProvider implements TestTemplateInvocationContextProvider {

  @Override
  public boolean supportsTestTemplate(ExtensionContext context) {
    return true;
  }

  @Override
  public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
    String testEnv = System.getProperty(END_TO_END_TESTING_ENVIRONMENT_KEY);
    return switch (testEnv) {
      case END_TO_END_TESTING_ENVIRONMENT_VALUE -> getContextForEnvironment(REAL_CALL_CONTEXT_DISPLAY_NAME);
      default -> getContextForEnvironment(MOCK_SERVER_CONTEXT_DISPLAY_NAME);
    };
  }

  private Stream<TestTemplateInvocationContext> getContextForEnvironment(String environment) {
    return Stream.of(new TestEnvironmentInvocationContext(environment));
  }
}
