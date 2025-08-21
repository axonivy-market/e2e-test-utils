package com.axonivy.utils.e2etest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import com.axonivy.utils.e2etest.context.MultiEnvironmentContextProvider;
import com.axonivy.utils.e2etest.context.TestEnvironmentInvocationContext;
import com.axonivy.utils.e2etest.properties.E2EProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.axonivy.utils.e2etest.enums.E2EEnvironment.REAL_SERVER;
import static com.axonivy.utils.e2etest.enums.E2EEnvironment.MOCK_SERVER;

public class TestMultiEnvironmentContextProvider {
  private final MultiEnvironmentContextProvider provider = new MultiEnvironmentContextProvider();

  @AfterEach
  void clearSystemProperties() {
    System.clearProperty(E2EProperty.KEY);
  }

  @Test
  void supportsTestTemplateShouldAlwaysReturnTrue() {
    assertTrue(provider.supportsTestTemplate(null));
  }

  @Test
  void shouldReturnRealContextWhenSystemPropertyMatches() {
    System.setProperty(E2EProperty.KEY, E2EProperty.DEFAULT_VALUE);

    Stream<TestTemplateInvocationContext> stream = provider.provideTestTemplateInvocationContexts(null);

    List<TestTemplateInvocationContext> contexts = stream.collect(Collectors.toList());

    assertEquals(1, contexts.size());
    assertTrue(contexts.get(0) instanceof TestEnvironmentInvocationContext);

    TestEnvironmentInvocationContext ctx = (TestEnvironmentInvocationContext) contexts.get(0);
    assertEquals(REAL_SERVER.getDisplayName(), ctx.getDisplayName(1));
  }

  @Test
  void shouldReturnMockContextWhenSystemPropertyIsDifferentOrUnset() {
    Stream<TestTemplateInvocationContext> stream = provider.provideTestTemplateInvocationContexts(null);

    List<TestTemplateInvocationContext> contexts = stream.collect(Collectors.toList());

    assertEquals(1, contexts.size());
    assertTrue(contexts.get(0) instanceof TestEnvironmentInvocationContext);

    TestEnvironmentInvocationContext ctx = (TestEnvironmentInvocationContext) contexts.get(0);
    assertEquals(MOCK_SERVER.getDisplayName(), ctx.getDisplayName(1));
  }
}
