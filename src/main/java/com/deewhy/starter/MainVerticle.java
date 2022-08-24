package com.deewhy.starter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;



public class MainVerticle extends AbstractVerticle {

    public static final String BASE_API_PATH = "/api/v1/services";

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        registerRoutes(router);
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
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
        .end("Hello from Vert.x!");

                });
    }
    
}