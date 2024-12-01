package com.burdantap.security

import com.burdantap.controller.endpoint.AuthEndpoint
import com.burdantap.controller.endpoint.ErrorEndpoint
import com.burdantap.domain.model.base.ErrorResponse
import com.burdantap.domain.model.securty.TokenType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity(
    jwtController: JWTManager
) {
    authentication {
        jwt(TokenType.ACCESS.value) {
            realm = jwtController.realm
            verifier(jwtController.verifyToken(tokenType = TokenType.ACCESS))
            validate { jwtCredential ->
                jwtController.customValidator(jwtCredential)
            }
            unauthorized()
        }
        jwt(TokenType.REFRESH.value) {
            realm = jwtController.realm
            verifier(jwtController.verifyToken(tokenType = TokenType.REFRESH))
            validate { jwtCredential ->
                jwtController.customValidator(jwtCredential)
            }
            refreshTokenForbidden()
        }
    }
}

private fun JWTAuthenticationProvider.Config.unauthorized() {
    challenge { _, _ ->
//        call.respondRedirect(ErrorEndpoint.Unauthorized.path)
        call.respond(
            message = ErrorResponse(
                code = HttpStatusCode.Unauthorized.value,
                message = "Unauthorized!!!"
            ),
            status = HttpStatusCode.Unauthorized
        )
    }
}

private fun JWTAuthenticationProvider.Config.refreshTokenForbidden() {
    challenge { _, _ ->
//        call.respondRedirect(ErrorEndpoint.Forbidden.path)
        call.respond(
            message = ErrorResponse(
                code = HttpStatusCode.Forbidden.value,
                message = "Forbidden!!!"
            ),
            status = HttpStatusCode.Forbidden
        )
    }
}