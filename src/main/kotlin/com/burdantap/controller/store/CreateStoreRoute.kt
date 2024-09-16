package com.burdantap.controller.store

import com.burdantap.data.repository.StoreRepository
import com.burdantap.domain.dto.StoreDto
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.StoreEndpoint
import com.burdantap.domain.model.securty.TokenType
import com.burdantap.security.securityVerifyPartnerContent
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Route.createStore(repository: StoreRepository) {
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