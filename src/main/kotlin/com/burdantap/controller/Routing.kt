package com.burdantap.controller

import com.burdantap.controller.authentication.partnerAuthenticationRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
    authentication()
}

private fun Application.authentication() {
    routing {
        partnerAuthenticationRoute()
    }
}
