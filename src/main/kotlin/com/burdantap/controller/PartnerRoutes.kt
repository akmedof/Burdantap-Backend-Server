package com.burdantap.controller

import com.burdantap.data.repository.PartnerRepository
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.ErrorEndpoint
import com.burdantap.domain.model.endpoint.PartnerEndpoint
import com.burdantap.domain.model.securty.TokenType
import com.burdantap.security.securityVerifyPartnerContent
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.partnerRoutes() {
    val repository by inject<PartnerRepository>()
    getPartnerInfo(repository)
}

private fun Route.getPartnerInfo(
    repository: PartnerRepository
) {
    authenticate(TokenType.ACCESS.value) {
        get(PartnerEndpoint.Read.path) {
            securityVerifyPartnerContent(call) { partnerId ->
                val partner = repository.readById(partnerId)
                if (partner != null) {
                    call.respond(
                        message = BaseResponse(data = partner),
                        status = HttpStatusCode.OK
                    )
                }else call.respondRedirect(ErrorEndpoint.NotFoundPartner.path)
            }
        }
    }
}