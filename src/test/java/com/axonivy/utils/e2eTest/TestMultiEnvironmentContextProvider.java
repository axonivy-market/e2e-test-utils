package com.axonivy.utils.e2eTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import com.axonivy.utils.e2eTest.context.MultiEnvironmentContextProvider;
import com.axonivy.utils.e2eTest.context.TestEnvironmentInvocationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.END_TO_END_TESTING_ENVIRONMENT_KEY;
import static com.axonivy.utils.e2eTest.constants.E2ETestConstants.END_TO_END_TESTING_ENVIRONMENT_VALUE;

public class TestMultiEnvironmentContextProvider {
  private final MultiEnvironmentContextProvider provider = new MultiEnvironmentContextProvider();

  @AfterEach
  void clearSystemProperties() {
    System.clearProperty(END_TO_END_TESTING_ENVIRONMENT_KEY);
  }

  @Test
  void supportsTestTemplateShouldAlwaysReturnTrue() {
    assertTrue(provider.supportsTestTemplate(null));
  }

  @Test
  void shouldReturnRealContextWhenSystemPropertyMatches() {
    System.setProperty(END_TO_END_TESTING_ENVIRONMENT_KEY, END_TO_END_TESTING_ENVIRONMENT_VALUE);

    Stream<TestTemplateInvocationContext> stream = provider.provideTestTemplateInvocationContexts(null);

    List<TestTemplateInvocationContext> contexts = stream.collect(Collectors.toList());

    assertEquals(1, contexts.size());
    assertTrue(contexts.get(0) instanceof TestEnvironmentInvocationContext);

    TestEnvironmentInvocationContext ctx = (TestEnvironmentInvocationContext) contexts.get(0);
    assertEquals(REAL_CALL_CONTEXT_DISPLAY_NAME, ctx.getDisplayName(1));
  }

  @Test
  void shouldReturnMockContextWhenSystemPropertyIsDifferentOrUnset() {
    Stream<TestTemplateInvocationContext> stream = provider.provideTestTemplateInvocationContexts(null);

    List<TestTemplateInvocationContext> contexts = stream.collect(Collectors.toList());

    assertEquals(1, contexts.size());
    assertTrue(contexts.get(0) instanceof TestEnvironmentInvocationContext);

    TestEnvironmentInvocationContext ctx = (TestEnvironmentInvocationContext) contexts.get(0);
    assertEquals(MOCK_SERVER_CONTEXT_DISPLAY_NAME, ctx.getDisplayName(1));
  }
}
