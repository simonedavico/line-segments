package io.github.linesegments.api

import io.github.linesegments.segments.Line
import io.github.linesegments.segments.Point
import io.github.linesegments.segments.Space
import io.vertx.core.Future

/**
 * An interface for an async line segments service
 *
 * @author Simone D'Avico on 26/11/2017.
 */
interface LineSegmentsService {

    fun addPoint(point: Point): Future<Space>

    fun getSpace(): Future<Space>

    fun clearSpace(): Future<Space>

    fun getLineSegments(numOfPoints: Int): Future<Set<Line>>

}