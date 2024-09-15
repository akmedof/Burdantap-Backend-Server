package com.burdantap.controller

import com.burdantap.data.repository.StoreRepository
import com.burdantap.domain.dto.StoreDto
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.ErrorEndpoint
import com.burdantap.domain.model.endpoint.StoreEndpoint
import com.burdantap.domain.model.securty.TokenType
import com.burdantap.security.securityVerifyPartnerContent
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.storeRoutes() {
    val repository: StoreRepository by inject<StoreRepository>()
    createStore(repository)
    getStore(repository)
}

private fun Route.createStore(repository: StoreRepository) {
    authenticate(TokenType.ACCESS.value) {
        post(StoreEndpoint.Create.path) {
            securityVerifyPartnerContent(call) { partnerId ->
                val request = call.receive<StoreDto>()
                val isCreated = repository.create(dto = request, partnerId = partnerId)
                application.log.info("STORE CREATED: $request")
                application.log.info("STORE PARTNER ID: $partnerId")
                call.respond(
                    message = BaseResponse(
                        success = isCreated,
                        data = if (isCreated) "CREATED" else "NOT_FOUND",
                    )
                )
            }
        }
    }
}

private fun Route.getStore(repository: StoreRepository) {
    authenticate(TokenType.ACCESS.value) {
        get(StoreEndpoint.Read.path) {
            securityVerifyPartnerContent(call){ partnerId ->
                val store = repository.read(partnerId)
                if (store != null) {
                    call.respond(
                        message = BaseResponse(
                            success = true,
                            data = store
                        )
                    )
                }else call.respondRedirect(ErrorEndpoint.NotFoundStore.path)
            }
        }
    }
}