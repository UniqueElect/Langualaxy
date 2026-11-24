package com.langualaxy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "langualaxy.tests")
public record TestProperties(int questionsPerAttempt) {}
