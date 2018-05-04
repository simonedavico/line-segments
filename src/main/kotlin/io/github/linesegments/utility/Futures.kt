package io.github.linesegments.utility

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.Future
import java.util.function.Supplier

/**
 * Future utility class
 *
 * @author Matteo Codogno on 15/12/2017
 */
class Futures {
    companion object {

        /**
         * Transforms Future<T> to CompletableFuture<T>
         */
        inline fun <reified T: Any> toCompletable(future: Future<T>, executor: Executor): CompletableFuture<T> {
            return CompletableFuture.supplyAsync(Supplier { future.get() }, executor)
        }

    }
}