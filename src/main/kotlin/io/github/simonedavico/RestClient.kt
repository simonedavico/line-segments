package io.github.simonedavico

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.simonedavico.dal.LinePatternRest
import io.github.simonedavico.utility.Futures
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val objectMapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
    val restClient = LinePatternRest()

    objectMapper.registerModule(KotlinModule()) // Enable Kotlin support

    val futureList = restClient
            .setSpace()
            .map {
                Futures.toCompletable(it, Executors.newSingleThreadExecutor())
            }

    CompletableFuture.allOf(*futureList.toTypedArray()).join()

    restClient
            .getSpace()
            .get()
            .apply { println("SPACE: $this") }

    restClient
            .getSegment(1)
            .get()
            .apply { println("SEGMENTS: $this") }

    exitProcess(0)
}