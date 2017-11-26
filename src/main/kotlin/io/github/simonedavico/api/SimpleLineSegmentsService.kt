package io.github.simonedavico.api

import io.github.simonedavico.segments.Line
import io.github.simonedavico.segments.Point
import io.github.simonedavico.segments.Space
import io.github.simonedavico.segments.findLineSegments
import io.vertx.core.Future

/**
 * @author Simone D'Avico on 26/11/2017.
 */
class SimpleLineSegmentsService: LineSegmentsService {

    private var space: Space = mutableSetOf()

    override fun addPoint(point: Point): Future<Space> {
        space = space.plus(point)
        return Future.succeededFuture(space)
    }

    override fun getSpace(): Future<Space> = Future.succeededFuture(space)

    override fun getLineSegments(numOfPoints: Int): Future<Set<Line>> {
        return Future.succeededFuture(findLineSegments(space, numOfPoints))
    }

}