package io.github.linesegments

import io.github.linesegments.api.ApiUtils
import io.github.linesegments.api.RestApi

fun main(args: Array<String>) {
    ApiUtils.runVerticle(RestApi::class.java)
}