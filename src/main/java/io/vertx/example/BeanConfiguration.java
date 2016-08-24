package io.vertx.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BeanConfiguration {

    private AppConfiguration appConfiguration;

    @Autowired
    public BeanConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    @Bean
    public StaticServer staticServer() {
        return new StaticServer(appConfiguration);
    }
}
