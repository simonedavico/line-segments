package io.github.simonedavico

import io.github.simonedavico.api.ApiUtils
import io.github.simonedavico.api.RestApi

fun main(args: Array<String>) {
    ApiUtils.runVerticle(RestApi::class.java)
}