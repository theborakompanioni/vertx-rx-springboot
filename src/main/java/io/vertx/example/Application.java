package io.vertx.example;


import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private StaticServer staticServer;

    @Autowired
    public Application(StaticServer staticServer) {
        this.staticServer = staticServer;
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(staticServer);
    }
}
