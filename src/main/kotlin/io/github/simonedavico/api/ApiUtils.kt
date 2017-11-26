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

    fun runVerticle(clazz: Class<out AbstractVerticle>) {

        System.setProperty("vertx.cwd", API_DIR)

        val vertxRunner: Consumer<Vertx> = Consumer { vertx ->
            try {
                vertx.deployVerticle(clazz.name)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }

        val vertx: Vertx = Vertx.vertx()
        vertxRunner.accept(vertx)

    }

}