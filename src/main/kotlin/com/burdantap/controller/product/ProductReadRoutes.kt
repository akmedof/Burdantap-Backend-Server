package com.burdantap.controller.product

import com.burdantap.domain.model.endpoint.ProductEndpoint
import io.ktor.server.routing.*

fun Route.productReadRoutes() {

}

private fun Route.readBySlug() {
    get(ProductEndpoint.Read.path) {

    }
}