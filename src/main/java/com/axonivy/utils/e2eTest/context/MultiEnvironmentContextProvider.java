package com.axonivy.utils.e2etest.context;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import com.axonivy.utils.e2etest.enums.E2EEnvironment;
import com.axonivy.utils.e2etest.properties.E2EProperty;

import static com.axonivy.utils.e2etest.enums.E2EEnvironment.REAL_SERVER;
import static com.axonivy.utils.e2etest.enums.E2EEnvironment.MOCK_SERVER;

public class MultiEnvironmentContextProvider implements TestTemplateInvocationContextProvider {

  @Override
  public boolean supportsTestTemplate(ExtensionContext context) {
    return true;
  }

  @Override
  public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
    E2EEnvironment environment =
        E2EProperty.DEFAULT_VALUE.equals(E2EProperty.getTestEnvironmentValue()) ? REAL_SERVER : MOCK_SERVER;
    return getContextForEnvironment(environment);
  }

  private Stream<TestTemplateInvocationContext> getContextForEnvironment(E2EEnvironment environment) {
    return Stream.of(new TestEnvironmentInvocationContext(environment.getDisplayName()));
  }
}
