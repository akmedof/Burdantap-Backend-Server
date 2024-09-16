package com.burdantap.controller.product

import io.ktor.server.routing.*

fun Route.productControllerRoutes() {
    productCreateRoute()
    productReadRoutes()
}