@file:OptIn(ExperimentalSerializationApi::class)

package com.burdantap.controller.product

import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi

fun Route.productControllerRoutes() {
    productCreateRoute()
    productReadRoutes()
    productCreateRouteNew()
}