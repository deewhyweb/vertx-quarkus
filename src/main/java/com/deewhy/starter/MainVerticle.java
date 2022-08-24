package com.deewhy.starter;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.mutiny.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;


@ApplicationScoped
public class MainVerticle extends AbstractVerticle {

    public static final String BASE_API_PATH = "/api/v1/services";


    public void init(@Observes StartupEvent e, Vertx vertx, MainVerticle verticle, Router router) {

        router.route().handler(BodyHandler.create());
        registerRoutes(router);
        vertx.deployVerticle(verticle).await().indefinitely();
    }


    private void registerRoutes(Router router) {
        router.route("/*").handler(StaticHandler.create());
        registerGetRoute(router);

    }


    private void registerGetRoute(Router router) {
        router.get(BASE_API_PATH)
                .handler(req -> {
                    req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from quarkus!");

                });
    }
    
}