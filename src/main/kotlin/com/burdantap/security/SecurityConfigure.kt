package com.burdantap.security

import com.burdantap.domain.model.endpoint.AuthEndpoint
import com.burdantap.domain.model.endpoint.ErrorEndpoint
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
        call.respondRedirect(ErrorEndpoint.Unauthorized.path)
    }
}

private fun JWTAuthenticationProvider.Config.refreshTokenForbidden() {
    challenge { _, _ ->
        call.respondRedirect(ErrorEndpoint.Forbidden.path)
    }
}