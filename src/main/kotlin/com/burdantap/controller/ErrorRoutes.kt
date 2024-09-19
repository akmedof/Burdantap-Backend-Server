package com.burdantap.controller

import com.burdantap.domain.model.base.ErrorResponse
import com.burdantap.domain.model.endpoint.ErrorEndpoint
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Route.errorRoutes() {
    authenticationErrorRoutes()
    unauthorizedErrorRoute()
    forbiddenErrorRoute()
    notFoundError(message = "Partner məlumatları tapılmadı.", endpoint = ErrorEndpoint.NotFoundPartner)
    notFoundError(message = "Store məlumatları tapılmadı.", endpoint = ErrorEndpoint.NotFoundStore)
}

private fun Route.authenticationErrorRoutes() {

}

private fun Route.notFoundError(
    message: String,
    endpoint: ErrorEndpoint,
) {
    get(endpoint.path){
        call.respond(
            message = ErrorResponse(
                code = HttpStatusCode.NotFound.value,
                message = message
            ),
            status = HttpStatusCode.NotFound
        )
    }
}

private fun Route.unauthorizedErrorRoute() {
    get(ErrorEndpoint.Unauthorized.path){
        call.respond(
            message = ErrorResponse(
                code = HttpStatusCode.Unauthorized.value,
                message = "Unauthorized!!!"
            ),
            status = HttpStatusCode.Unauthorized
        )
    }
}

private fun Route.forbiddenErrorRoute(){
    get(ErrorEndpoint.Forbidden.path){
        call.respond(
            message = ErrorResponse(
                code = HttpStatusCode.Forbidden.value,
                message = "Sizin bu melumata catma icazeniz yoxdu."
            ),
            status = HttpStatusCode.Forbidden
        )
    }
}