package io.vertx.example;

import com.google.common.annotations.VisibleForTesting;
import io.vertx.core.Handler;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.*;


final class RouteHandlers {
    private RouteHandlers() {
        throw new UnsupportedOperationException();
    }

    static Handler<RoutingContext> notFoundHandler() {
        return ctx -> {
            ctx.fail(404);
        };
    }

    static LoggerHandler loggerHandler() {
        return LoggerHandler.create();
    }

    static ErrorHandler failureHandler() {
        boolean displayExceptionDetails = false;
        return ErrorHandler.create(displayExceptionDetails);
    }

    static ResponseTimeHandler responseTimeHandler() {
        return ResponseTimeHandler.create();
    }

    static TimeoutHandler timeoutHandler() {
        return TimeoutHandler.create(10_000);
    }

    static BodyHandler bodyHandler() {
        return BodyHandler.create();
    }

    @VisibleForTesting
    static Handler<RoutingContext> internalServerErrorHandler() {
        return ctx -> {
            throw new RuntimeException("Simulated Internal Server Error");
        };
    }
}
