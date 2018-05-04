package io.github.linesegments.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import java.util.function.Consumer

/**
 * An utility object to quickly deploy a verticle
 *
 * @author Simone D'Avico on 26/11/2017.
 */
object ApiUtils {

    private val API_DIR = "src/main/kotlin/io/github/simonedavico/api"

    /**
     * Runs a Vert.x verticle. Very simple implementation, makes
     * assumptions as to where the verticle is located in the project
     *
     * @param verticleClass the verticle class
     */
    fun runVerticle(verticleClass: Class<out AbstractVerticle>) {

        System.setProperty("vertx.cwd", API_DIR)

        val vertxRunner: Consumer<Vertx> = Consumer { vertx ->
            try {
                vertx.deployVerticle(verticleClass.name)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }

        val vertx: Vertx = Vertx.vertx()
        vertxRunner.accept(vertx)

    }

    /**
     * Takes a json object and a decoder, and applies the decoder to the object
     *
     * @param obj the json object to decode
     * @param decoder a decoder function
     * @return the decoded object
     */
    fun <T> fromJson(obj: JsonObject, decoder: (JsonObject) -> T) = obj.let {
        decoder(it)
    }

}

/**
 * A simple data class for an error message
 */
data class ErrorMessage(val message: String)