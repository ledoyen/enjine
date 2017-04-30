package com.github.ledoyen.enjine.discovery;

import java.util.Set;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscoveryTest {

    @Test
    public void java_services_discovery() {
        Set<Class<TestService>> classes = Discovery.fromJavaService(TestService.class);

        assertThat(classes).hasSize(2);
    }

    public interface TestService {}

    public static class TestService1 implements TestService{}
    public static class TestService2 implements TestService{}
}
