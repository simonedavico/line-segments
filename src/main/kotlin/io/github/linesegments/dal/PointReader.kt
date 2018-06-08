package io.github.linesegments.dal

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.linesegments.bl.segments.Point
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject

/**
 * @author Matteo Codogno on 03.06.18
 */
inline fun <reified T : Any> getPointsFromYamlFile(filename: String) : T {
    val mapper = ObjectMapper(YAMLFactory())
    mapper.registerModule(KotlinModule())

    val seedInputStream = LinePatternRest::class.java.classLoader
            .getResource(filename)
            .openStream()

    return mapper.readValue(seedInputStream)
}

fun getPointFromJson(json: JsonObject) = Point(json.getInteger("x"), json.getInteger("y"))

fun getPointsFromJsonString(json: String) = HashSet(JsonArray(json).map {
    HashSet((it as JsonArray).map {
        getPointFromJson(it as JsonObject)
    })
})