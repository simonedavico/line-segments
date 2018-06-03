package io.github.linesegments.api

import io.github.linesegments.bl.segments.Line
import io.github.linesegments.bl.segments.Point
import io.github.linesegments.bl.segments.Space
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