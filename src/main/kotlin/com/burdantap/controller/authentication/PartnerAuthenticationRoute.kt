package com.burdantap.controller.authentication

import com.burdantap.data.repository.PartnerRepository
import com.burdantap.domain.dto.partner.PartnerDto
import com.burdantap.domain.dto.partner.PartnerLoginDto
import com.burdantap.domain.model.endpoint.AuthEndpoint
import com.burdantap.security.hasing.SHA256HashingService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.partnerAuthenticationRoute() {
    val repository: PartnerRepository by inject(PartnerRepository::class.java)
    login(repository)
    register(repository)
}

private fun Route.login(
    repository: PartnerRepository
){
    post(AuthEndpoint.PartnerLogin.path) {
        val request = call.receive<PartnerLoginDto>()
        val partner = repository.checkEmailAndPassword(request)
        application.log.info("LOGIN: $request")
        application.log.info("LOGIN PASS HASH: ${SHA256HashingService().generateSaltedHash(request.password)}")
        application.log.info("LOGIN CHECK: $partner")
        if (partner != null) {
            call.respond(
                message = partner,
                status = HttpStatusCode.OK
            )
        }else{
            call.respond(
                message = "Bu ${request.email} uzre Partner tapilmadi",
                status = HttpStatusCode.NotFound
            )
        }
    }
}

private fun Route.register(
    repository: PartnerRepository
){
    post(AuthEndpoint.PartnerRegister.path) {
        val request = call.receive<PartnerDto>()
        val checkEmail = repository.create(request)
        application.log.info("REQUEST: $request")
        if (checkEmail){
            call.respond(
                message = "Email yoxdur",
                status = HttpStatusCode.OK
            )
        }else{
            call.respond(
                message = "Email Var",
                status = HttpStatusCode.BadRequest
            )
        }
    }
}