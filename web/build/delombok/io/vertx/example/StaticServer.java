// Generated by delombok at Sat Jan 20 12:35:44 CET 2018
package io.vertx.example;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.StaticHandler;
import org.springframework.beans.factory.annotation.Autowired;
import static java.util.Objects.requireNonNull;

class StaticServer extends AbstractVerticle {
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StaticServer.class);
    private AppConfiguration configuration;

    @Autowired
    public StaticServer(AppConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        String webroot = configuration.webroot();
        log.info("Using \'{}\' as static webroot", webroot);
        router.route().handler(RouteHandlers.loggerHandler());
        router.route().handler(RouteHandlers.timeoutHandler());
        router.route().handler(RouteHandlers.responseTimeHandler());
        router.route().failureHandler(RouteHandlers.failureHandler());
        router.route().handler(StaticHandler.create(webroot));
        vertx.createHttpServer().requestHandler(router::accept).listen(configuration.httpPort());
    }
}