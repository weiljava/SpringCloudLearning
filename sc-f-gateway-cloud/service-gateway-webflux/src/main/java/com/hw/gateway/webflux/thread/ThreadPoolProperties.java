package com.hw.gateway.webflux.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "custom-executor-service")
public class ThreadPoolProperties {

    private int corePoolSize = 10;

    private int maximumPoolSize = 10;

    private long keepAliveTime = 0L;

    private int capacity = 1024;

    private String artifactId;
}
