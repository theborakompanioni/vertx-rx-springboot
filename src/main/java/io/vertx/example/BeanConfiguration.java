package io.vertx.example;

import io.vertx.rxjava.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import static java.util.Objects.requireNonNull;

@Configuration
class BeanConfiguration {

    private AppConfiguration appConfiguration;

    @Autowired
    public BeanConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = requireNonNull(appConfiguration);
    }

    @Bean
    public Vertx vertx() {
        return Vertx.vertx();
    }

    @Bean
    @Order(1)
    public StaticServer staticServer() {
        return new StaticServer(appConfiguration);
    }
}
