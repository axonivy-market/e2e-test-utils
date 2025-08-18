package com.axonivy.utils.e2eTest.context;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.END_TO_END_TESTING_ENVIRONMENT_KEY;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.END_TO_END_TESTING_ENVIRONMENT_VALUE;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME;

public class MultiEnvironmentContextProvider implements TestTemplateInvocationContextProvider {

  @Override
  public boolean supportsTestTemplate(ExtensionContext context) {
    return true;
  }

  @Override
  public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
    String testEnv = System.getProperty(END_TO_END_TESTING_ENVIRONMENT_KEY);
    if (testEnv == END_TO_END_TESTING_ENVIRONMENT_VALUE) {
      return getContextForEnvironment(REAL_CALL_CONTEXT_DISPLAY_NAME);
    }
    return getContextForEnvironment(MOCK_SERVER_CONTEXT_DISPLAY_NAME);
  }

  private Stream<TestTemplateInvocationContext> getContextForEnvironment(String environment) {
    return Stream.of(new TestEnvironmentInvocationContext(environment));
  }

}
