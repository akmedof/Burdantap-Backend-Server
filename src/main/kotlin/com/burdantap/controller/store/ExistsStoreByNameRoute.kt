package com.burdantap.controller.store

import com.burdantap.controller.endpoint.StoreEndpoint
import com.burdantap.data.repository.StoreRepository
import com.burdantap.domain.model.base.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.existsStoreByName(repository: StoreRepository) {
    post(StoreEndpoint.Exist.path) {
        val name = call.request.queryParameters["name"] ?: ""
        val existsStore = repository.existsStoreByName(name)
        call.respond(
            message = BaseResponse(
                success = !existsStore,
                data = if (existsStore) "Store with exist $name" else "Store doesn't exist",
            )
        )
    }
}