package io.vertx.example;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.StaticHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class StaticServer extends AbstractVerticle {

    private AppConfiguration configuration;

    @Autowired
    public StaticServer(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        String webroot = getRoot();
        log.info("Using '{}' as webroot", webroot);

        router.route().handler(StaticHandler.create(webroot));

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(configuration.httpPort());
    }

    private String getRoot() {
        final String pathWhenInsideJarFile = "BOOT-INF/classes/webroot";
        boolean insideJarFile = new ClassPathResource(pathWhenInsideJarFile).exists();
        return insideJarFile ? pathWhenInsideJarFile : "webroot";
    }
}
