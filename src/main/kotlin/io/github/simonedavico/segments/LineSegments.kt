package io.github.simonedavico.segments

/**
 * @author Simone D'Avico on 25/11/2017.
 */
fun main(args: Array<String>) {

    val p1 = Point(0, 10)
    val p2 = Point(10, 20)
    val m = p1.computeSlopeWith(p2)
    val theta = p1.computeAngleWith(p2)

    println(m)
    println(theta)

}