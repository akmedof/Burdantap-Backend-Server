package com.burdantap.controller.authentication

import com.burdantap.data.repository.PartnerRepository
import com.burdantap.domain.dto.partner.PartnerDto
import com.burdantap.domain.dto.partner.PartnerLoginDto
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.base.ErrorResponse
import com.burdantap.domain.model.endpoint.AuthEndpoint
import com.burdantap.domain.model.endpoint.ErrorEndpoint
import com.burdantap.security.JWTManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.partnerAuthenticationRoute() {
    val jwtManager = JWTManager(application)
    val repository: PartnerRepository by inject(PartnerRepository::class.java)
    login(jwtManager, repository)
    register(jwtManager, repository)
}

private fun Route.login(
    jwtManager: JWTManager,
    repository: PartnerRepository
){
    post(AuthEndpoint.PartnerLogin.path) {
        val request = call.receive<PartnerLoginDto>()
        val partner = repository.checkEmailAndPassword(request)
        if (partner != null) {
            call.respond(
                message = BaseResponse(
                    success = true,
                    data = jwtManager.createPartnerToken(partner)
                ),
                status = HttpStatusCode.OK
            )
        }else{
            call.respondRedirect(ErrorEndpoint.NotFoundPartner.path)
        }
    }
}

private fun Route.register(
    jwtManager: JWTManager,
    repository: PartnerRepository
){
    post(AuthEndpoint.PartnerRegister.path) {
        val request = call.receive<PartnerDto>()
        val checkEmail = repository.checkEmail(request.email)
        if (!checkEmail){
            val partner = repository.create(request)
            if (partner != null) {
                call.respond(
                    message = BaseResponse(
                        success = true,
                        data = jwtManager.createPartnerToken(partner)
                    ),
                    status = HttpStatusCode.Created
                )
            }else{
                call.respond(
                    message = ErrorResponse(
                        code = HttpStatusCode.BadRequest.value,
                        message = "Register error!!!"
                    ),
                    status = HttpStatusCode.BadRequest
                )
            }
        }else{
            call.respond(
                message = ErrorResponse(
                    code = HttpStatusCode.BadRequest.value,
                    message = "${request.email} already exists"
                ),
                status = HttpStatusCode.BadRequest
            )
        }
    }
}