package io.github.simonedavico.segments

/**
 * @author Simone D'Avico on 25/11/2017.
 */

/**
 * Given a origin and a space, groups the points in the space according to the angle
 * between them and the origin
 *
 * @param origin a Point acting as origin
 * @param space a space of points
 */
fun groupByAngle(origin: Point, space: Space) =
    (space.minus(origin))
        .map { p2 -> origin.computeAngleWith(p2) to p2 }
        .groupBy({ it.first }, { it.second })

/**
 * Given a space, considers each point as origin and groups the other points according
 * to the angle between them and the origin
 *
 * @param space a space of points
 */
fun groupSpaceByAngle(space: Space) =
    space
        .map { p -> p to groupByAngle(p, space) }
        .groupBy({ it.first }, { it.second })


fun main(args: Array<String>) {
    val p1 = Point(0, 10)
    val space = setOf(p1, Point(10, 20), Point(20, 30))
    println(groupByAngle(p1, space))
    println(groupSpaceByAngle(space))
}