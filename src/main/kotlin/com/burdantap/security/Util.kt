package com.burdantap.security

import com.burdantap.domain.model.endpoint.ErrorEndpoint
import com.burdantap.domain.model.securty.AccessRole
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun extractPrincipalUserId(call: ApplicationCall): String? = call.principal<JWTPrincipal>()
    ?.payload
    ?.getClaim("userId")
    ?.asString()

fun extractPrincipalRoleType(call: ApplicationCall): String? = call.principal<JWTPrincipal>()
    ?.payload
    ?.getClaim("role")
    ?.asString()

suspend fun securityVerifyPartnerContent(call: ApplicationCall, body: suspend (String) -> Unit) {
    extractPrincipalUserId(call)?.let { id ->
        extractPrincipalRoleType(call)?.let { roleType ->
            if (roleType == AccessRole.PARTNER.name) {
                body(id)
            }else{
                call.respondRedirect(ErrorEndpoint.Forbidden.path)
            }
        }
    }
}

suspend fun securityVerifyCustomerContent(call: ApplicationCall, body: suspend (String) -> Unit) {
    extractPrincipalUserId(call)?.let { id ->
        extractPrincipalRoleType(call)?.let { roleType ->
            if (roleType == AccessRole.CUSTOMER.name) {
                body(id)
            }else{
                call.respondRedirect(ErrorEndpoint.Forbidden.path)
            }
        }
    }
}