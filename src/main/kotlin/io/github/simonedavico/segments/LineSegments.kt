package io.github.simonedavico.segments

/**
 * @author Simone D'Avico on 25/11/2017.
 */
typealias Line = Set<Point>

/**
 * Given a origin and a space, groups the points in the space according to the angle
 * between them and the origin
 *
 * @param origin a Point acting as origin
 * @param space a space of points
 * @return a mapping angle -> points
 */
fun groupByAngle(origin: Point, space: Space): Map<Double, Set<Point>> =
    (space.minus(origin))
        .map { origin.computeAngleWith(it) to it }
        .groupBy({ it.first }, { it.second })
        .mapValues { (_, value) -> value.toSet() }


/**
 *  @param origin the reference for which to search for collinear points
 *  @param space a space of points
 *  @param minPoints the minimum number of points the segment should have
 *  @return a set of line segments
 */
fun getAllSegmentsForPoint(origin: Point, space: Space, minPoints: Int): Set<Line> =
     groupByAngle(origin, space).values
             .filter { it.size >= minPoints - 1 }
             .map { it.plus(origin) }
             .toSet()


/**
 * Finds all line segments in a space with the specified number of points
 *
 * @param space a space of points
 * @param minPoints the number of points in the segment
 * @return a set of line segments
 */
fun findLineSegments(space: Space, minPoints: Int = 4): Set<Line> =
    space
        .flatMap { getAllSegmentsForPoint(it, space, minPoints) }
        .toSet()


fun main(args: Array<String>) {
    val p1 = Point(0, 10)
    val space = setOf(
            p1, Point(-10, 0), Point(10, 20), Point(20, 30),
            Point(0, 1), Point(0, 2), Point(0, 3), Point(0, 4)
    )

    println()
    space.forEach {
        println(it to groupByAngle(it, space))
    }
    println()

    println(findLineSegments(space))
}