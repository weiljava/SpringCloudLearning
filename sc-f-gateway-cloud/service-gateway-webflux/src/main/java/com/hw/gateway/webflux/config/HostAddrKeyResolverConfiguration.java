package com.hw.gateway.webflux.config;


import com.hw.gateway.webflux.filter.HostAddrKeyResolver;
import com.hw.gateway.webflux.filter.UriKeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

@Configuration
public class HostAddrKeyResolverConfiguration {
    /**
     * 根据请求IP进行限流
     *
     * @return
     */
    @Bean
    @Primary
    HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }

    /**
     * 根据URI限流
     *
     * @return
     */
    @Bean
    UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }


    /**
     * 根据参数内的请求信息去限流（用户）
     *
     * @return
     */
    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }
}
