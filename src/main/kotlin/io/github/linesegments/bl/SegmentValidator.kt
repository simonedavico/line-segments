package io.github.linesegments.bl

import io.github.linesegments.bl.segments.Line
import io.github.linesegments.dal.getPointsFromYamlFile

/**
 * @author Matteo Codogno on 03.06.18
 */
fun compareWithSnapshot(segmentLength: Int, lines: Set<Line>) {
    val snapshotLines = getPointsFromYamlFile<Set<Line>>("output_$segmentLength.yml")

    assert(snapshotLines.size == lines.size) { "$segmentLength - Divergent size" }
    lines.map {
        assert(snapshotLines.contains(it)) { "$segmentLength - $it does not exist in snapshot lines" }
    }
}