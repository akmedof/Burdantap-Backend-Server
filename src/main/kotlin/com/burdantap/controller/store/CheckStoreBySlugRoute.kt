package com.burdantap.controller.store

import com.burdantap.data.repository.StoreRepository
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.StoreEndpoint
import com.burdantap.domain.model.securty.TokenType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Route.checkStoreBySlugRoute(repository: StoreRepository) {
    authenticate(TokenType.ACCESS.value) {
        get(StoreEndpoint.Check.path) {
            val slug = call.request.queryParameters["slug"] ?: return@get call.respond(
                message = "Query parameter 'slug' missing or not a valid slug",
                status = HttpStatusCode.BadRequest
            )
            val isExist = repository.existsStoreBySlug(slug)
            call.respond(
                message = BaseResponse(
                    success = true,
                    data = isExist
                )
            )
        }
    }
}