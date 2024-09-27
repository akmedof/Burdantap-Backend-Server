package com.burdantap.controller

import com.burdantap.controller.authentication.partnerAuthenticationRoute
import com.burdantap.controller.color.colorRoutes
import com.burdantap.controller.file.fileRoutes
import com.burdantap.controller.product.productControllerRoutes
import com.burdantap.controller.store.storeRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    authentication()
}

private fun Application.authentication() {
    routing {
        partnerAuthenticationRoute()
        errorRoutes()
        partnerRoutes()
        storeRoutes()
        productControllerRoutes()
        colorRoutes()
        fileRoutes()
    }
}
