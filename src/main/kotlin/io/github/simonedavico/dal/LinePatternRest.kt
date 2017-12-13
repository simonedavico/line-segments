package io.github.simonedavico.dal

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.simonedavico.dal.rest.kotlin.get
import io.github.simonedavico.segments.Point
import org.glassfish.jersey.client.JerseyClientBuilder
import org.glassfish.jersey.jackson.JacksonFeature
import java.util.concurrent.Future
import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author Matteo Codogno on 29/11/2017
 */
class LinePatternRest {
    companion object {
        private val REST_SERVICE_URL = "http://localhost:3000"
    }

    private val client: Client = JerseyClientBuilder.newClient().register(JacksonFeature::class.java)

    fun setPoint(point: Point): Future<Response> {
        return client.target("$REST_SERVICE_URL/point")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .async()
                .post(Entity.entity(point, MediaType.APPLICATION_JSON_TYPE))
    }

    fun setSpace(): List<Future<Response>> {
        val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing

        mapper.registerModule(KotlinModule()) // Enable Kotlin support

        val seedInputStream = LinePatternRest::class.java.classLoader
                .getResource("points_001.yml")
                .openStream()

        return mapper.readValue<Set<Point>>(seedInputStream).map {
            this.setPoint(it)
        }

    }

    fun getSpace(): Future<String> {
        return client.target("$REST_SERVICE_URL/space")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .async()
                .get<String>()
    }

    fun getSegment(numberOfPoints: Int): Future<String> {
        return client.target("$REST_SERVICE_URL/lines/$numberOfPoints")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .async()
                .get<String>()
    }
}
