package io.github.linesegments.api

import io.github.linesegments.bl.segments.Line
import io.github.linesegments.bl.segments.Point
import io.github.linesegments.bl.segments.Space
import io.github.linesegments.bl.segments.findLineSegments
import io.vertx.core.Future

/**
 * An implementation for a simple async line segments service.
 * Points are stored in a set in memory.
 *
 * @author Simone D'Avico on 26/11/2017.
 */
class SimpleLineSegmentsService: LineSegmentsService {
    private var space: Space = setOf()

    override fun addPoint(point: Point): Future<Space> {
        space = space.plus(point)
        return Future.succeededFuture(space)
    }

    override fun getSpace(): Future<Space> = Future.succeededFuture(space)

    override fun clearSpace(): Future<Space> {
        space = setOf()
        return Future.succeededFuture(space)
    }

    override fun getLineSegments(numOfPoints: Int): Future<Set<Line>> {
        return Future.succeededFuture(findLineSegments(space, numOfPoints))
    }

}