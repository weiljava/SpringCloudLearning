package com.hw.gateway.webflux.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Configuration
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolAutoConfiguration {

    @Resource
    private ThreadPoolProperties properties;

    @Bean
    public ThreadFactory threadFactory() {
        return new ThreadFactoryBuilder().setNameFormat(properties.getArtifactId() + "-pool-%d").build();
    }

    public ExecutorService customExecutorService(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(properties.getCorePoolSize(), properties.getMaximumPoolSize(),
                properties.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(properties.getCapacity()), threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
