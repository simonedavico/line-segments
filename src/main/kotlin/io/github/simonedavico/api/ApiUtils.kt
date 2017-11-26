package io.github.simonedavico.api

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
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

}