package com.burdantap.controller.util

import com.burdantap.controller.endpoint.CommonEndpoint
import com.burdantap.data.repository.PartnerRepository
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.base.ErrorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.checkEmailRoute(){
    val partner: PartnerRepository by inject<PartnerRepository>()
    post(CommonEndpoint.CheckEmail.route) {
        val email = call.parameters["email"] ?: ""
        if (!partner.checkEmail(email)) {
            call.respond(
                message = BaseResponse(
                    success = true,
                    data = "This email is available."
                )
            )
        }else{
            call.respond(
                message = ErrorResponse(
                    code = HttpStatusCode.Conflict.value,
                    message = "This email is already registered."
                )
            )
        }
    }
}