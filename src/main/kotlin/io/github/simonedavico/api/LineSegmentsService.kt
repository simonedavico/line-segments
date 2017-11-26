package io.github.simonedavico.api

import io.github.simonedavico.segments.Line
import io.github.simonedavico.segments.Point
import io.github.simonedavico.segments.Space
import io.vertx.core.Future

/**
 * An interface for an async line segments service
 *
 * @author Simone D'Avico on 26/11/2017.
 */
interface LineSegmentsService {

    fun addPoint(point: Point): Future<Space>

    fun getSpace(): Future<Space>

    fun getLineSegments(numOfPoints: Int): Future<Set<Line>>

}