package io.vertx.example;

import com.google.common.collect.ImmutableList;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private Vertx vertx;
    private List<Verticle> verticles;

    @Autowired
    public Application(Vertx vertx, List<Verticle> verticles) {
        this.vertx = vertx;
        this.verticles = ImmutableList.copyOf(verticles);
    }

    @PostConstruct
    public void deployVerticle() {
        verticles.forEach(verticle -> vertx.deployVerticle(verticle));
    }
}
