package io.github.simonedavico.api

import io.github.simonedavico.segments.Point
import io.vertx.core.AbstractVerticle
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

/**
 * A Vert.x verticle that exposes the rest api required to interact with our space
 *
 * @author Simone D'Avico on 26/11/2017.
 */
class RestApi: AbstractVerticle() {

    private val json = "application/json"
    private val port = 8080

    private val lineSegments = SimpleLineSegmentsService()

    override fun start() {

        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())

        router.post("/point").consumes(json).handler { ctx ->

            val point = ApiUtils.fromJson(ctx.bodyAsJson) { json ->
                Point(json.getInteger("x"), json.getInteger("y"))
            }

            lineSegments.addPoint(point).setHandler({
                ctx.response().end(Json.encodePrettily(it.result()))
            })

        }

        router.get("/lines/:n").produces(json).handler { ctx ->
            lineSegments.getLineSegments(ctx.pathParam("n").toInt()).setHandler {
                ctx.response().end(Json.encodePrettily(it.result()))
            }
        }

        router.get("/space").produces(json).handler { ctx ->
            lineSegments.getSpace().setHandler {
                ctx.response().end(Json.encodePrettily(it.result()))
            }
        }

        router.route().last().handler { ctx ->
            ctx.response().setStatusCode(404).end(
                    Json.encodePrettily(ErrorMessage("Route not found"))
            )
        }

        vertx.createHttpServer().requestHandler(router::accept).listen(port) {
            if (it.succeeded()) println("Server listening at port $port...")
            else println(it.cause())
        }

    }

}

fun main(args: Array<String>) {
    ApiUtils.runVerticle(RestApi::class.java)
}

