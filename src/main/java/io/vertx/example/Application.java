package io.vertx.example;

import com.google.common.collect.ImmutableList;
import io.vertx.core.Verticle;
import io.vertx.rxjava.core.RxHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Observable;
import rx.observables.BlockingObservable;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private io.vertx.rxjava.core.Vertx vertx;
    private List<Verticle> verticles;

    @Autowired
    public Application(io.vertx.rxjava.core.Vertx vertx, List<Verticle> verticles) {
        this.vertx = requireNonNull(vertx);
        this.verticles = ImmutableList.copyOf(verticles);
    }

    @PostConstruct
    public void deployVerticle() {
        Observable.from(verticles)
                .map(verticle -> RxHelper.deployVerticle(vertx, verticle))
                .map(Observable::toBlocking)
                .map(BlockingObservable::single)
                .forEach(response -> {
                    log.debug("deployed verticle {}", response);
                });
    }
}
