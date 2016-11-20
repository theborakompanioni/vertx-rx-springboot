package io.vertx.example;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.StaticHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Objects.requireNonNull;

@Slf4j
class StaticServer extends AbstractVerticle {

    private AppConfiguration configuration;

    @Autowired
    public StaticServer(AppConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        String webroot = configuration.webroot();
        log.info("Using '{}' as static webroot", webroot);

        router.route().handler(StaticHandler.create(webroot));

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(configuration.httpPort());
    }
}
