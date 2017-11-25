package io.github.simonedavico.segments

/**
 * @author Simone D'Avico on 25/11/2017.
 *
 * A type representing a point in the 2-dimensional Euclidean space
 */
typealias Point = Pair<Int, Int>

val Point.x
    get() = first

val Point.y
    get() = second

/**
 * Computes the slope between this point and another one
 *
 * @param point the other point
 */
fun Point.computeSlopeWith(point: Point): Double = (point.y - this.y).toDouble()/(point.x - this.x)

/**
 * Computes the angle in degrees between this point and another one
 *
 * @param point the other point
 */
fun Point.computeAngleWith(point: Point) = Math.toDegrees(Math.atan(computeSlopeWith(point)))
