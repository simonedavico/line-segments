package io.github.simonedavico.api

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router

/**
 * A Vert.x verticle that exposes the rest api required to interact with our space
 *
 * @author Simone D'Avico on 26/11/2017.
 */
class RestApi: AbstractVerticle() {

    override fun start() {

        val router = Router.router(vertx)

        //TODO: register handlers
        router.get().handler({ ctx ->
            ctx.response().putHeader("content-type", "text/plain").end("Hello vert.x!")
        })

        vertx.createHttpServer().requestHandler(router::accept).listen(8080)

    }

}

fun main(args: Array<String>) {
    ApiUtils.runVerticle(RestApi::class.java)
}

