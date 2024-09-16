package com.burdantap.controller.store

import com.burdantap.data.repository.StoreRepository
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.ErrorEndpoint
import com.burdantap.domain.model.endpoint.StoreEndpoint
import com.burdantap.domain.model.securty.TokenType
import com.burdantap.security.securityVerifyPartnerContent
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Route.readStoreByPartnerToken(repository: StoreRepository) {
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