package io.github.linesegments.bl

import io.github.linesegments.api.SimpleLineSegmentsService
import io.github.linesegments.bl.segments.Line
import io.github.linesegments.bl.segments.Point
import io.github.linesegments.dal.getPointsFromYamlFile

/**
 * @author Matteo Codogno on 03.06.18
 */
fun compareWithSnapshot(segmentLength: Int, lines: Set<Line>) = validate(segmentLength)(lines)(getPointsFromYamlFile("output_$segmentLength.yml"))

fun compareWithPrefixedInput(segmentLength: Int, lines: Set<Line>) = validate(segmentLength)(lines)(getPointsFromYamlFile("output_$segmentLength.yml"))

//private fun getSegments(segmentLength: Int) : Set<Line> {
//    val lineSegments = SimpleLineSegmentsService()
//
//    getPointsFromYamlFile<Set<Point>>("points_001.yml").map {
//        lineSegments.addPoint(it)
//    }
//
//    lineSegments.getLineSegments(segmentLength)
//}

private fun validate(segmentLength: Int) = {
    lines: Set<Line> -> {
        snapshotLines: Set<Line> ->
            assert(snapshotLines.size == lines.size) { "$segmentLength - Divergent size" }
            lines.map {
                assert(snapshotLines.contains(it)) { "$segmentLength - $it does not exist in snapshot lines" }
            }
    }
}