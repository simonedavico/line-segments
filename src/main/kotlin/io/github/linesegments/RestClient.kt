package io.github.linesegments

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.linesegments.bl.compareWithSnapshot
import io.github.linesegments.bl.segments.Line
import io.github.linesegments.dal.LinePatternRest
import io.github.linesegments.dal.getPointFromJson
import io.github.linesegments.dal.getPointsFromJsonString
import io.github.linesegments.utility.Futures
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Endpoint ${args[0]}")

    val objectMapper = ObjectMapper(YAMLFactory())  // Enable YAML parsing
    val restClient = LinePatternRest(args[0])

    objectMapper.registerModule(KotlinModule())     // Enable Kotlin support

    restClient
            .deleteSpace()
            .get()

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
            .getSegment(3)
            .get()
            .apply {
                val lines: Set<Line> = getPointsFromJsonString(this)

                compareWithSnapshot(3, lines)
            }

    exitProcess(0)
}