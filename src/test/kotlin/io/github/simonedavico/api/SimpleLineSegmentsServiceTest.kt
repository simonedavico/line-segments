package io.github.simonedavico.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.simonedavico.segments.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

/**
 * @author Matteo Codogno on 28/11/2017
 */
internal class SimpleLineSegmentsServiceTest {
    @Test
    fun getEmptySpace() {
        val segmentService = SimpleLineSegmentsService()
        segmentService.getSpace().setHandler { assertThat(it.result()).isEmpty() }
    }

    @Test
    fun addSomePoints() {
        val segmentService = SimpleLineSegmentsService()
        segmentService.addPoint(Point(1, 4))
        segmentService.addPoint(Point(9, -24))
        segmentService.addPoint(Point(7, 210))
        segmentService.addPoint(Point(7, 210))

        // TODO: change the coordinates types form int to double
//        segmentService.addPoint(Point(3.2, 4))

        segmentService.getSpace().setHandler {
            assertThat(it.result())
                .isNotNull
                .isNotEmpty
                .hasSize(3)
        }
    }

    @ParameterizedTest
    @MethodSource("lines")
    fun getSimpleLineSegment(input: Int, expected: Int) {
        val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
        mapper.registerModule(KotlinModule()) // Enable Kotlin support

        val segmentService = SimpleLineSegmentsService()
        val seedInputStream = SimpleLineSegmentsServiceTest::class.java.classLoader
                .getResource("points_001.yml")
                .openStream()
        mapper.readValue<Set<Point>>(seedInputStream).map {
            segmentService.addPoint(it)
        }

        segmentService.getLineSegments(input).setHandler {
            assertThat(it.result())
                    .isNotNull
                    .hasSize(expected)
        }
    }

    companion object {
        @JvmStatic
        fun lines() = listOf(
                // Minimum number of points for each segment, expected segments
                Arguments.of(5, 0),
                Arguments.of(4, 1),
                Arguments.of(3, 6)
        )
    }
}