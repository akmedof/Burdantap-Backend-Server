package com.burdantap.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.burdantap.domain.model.response.PartnerResponse
import com.burdantap.domain.model.securty.AccessRole
import com.burdantap.domain.model.response.TokenResponse
import com.burdantap.domain.model.securty.TokenType
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import java.lang.System.getenv
import java.util.*

class JWTManager(
    private val application: Application
) {
    private val secret = getenv("JWT_SECRET")
    private val issuer = getConfigProperty("jwt.issuer")
    private val audience = getConfigProperty("jwt.audience")
    val realm = getConfigProperty("jwt.realm")

//    fun createCustomerToken(customer: CustomerDto): TokenResponse =
//        TokenResponse(
//            access = generateToken(
//                id = customer.uuid,
//                role = AccessRole.CUSTOMER,
//                tokenType = TokenType.ACCESS_TOKEN,
//                expirationDate = 60 * 60 * 1000
//            ),
//            refresh = generateToken(
//                id = customer.uuid,
//                role = AccessRole.CUSTOMER,
//                tokenType = TokenType.REFRESH_TOKEN,
//                expirationDate = 24 * 60 * 60 * 1000
//            )
//        )

    fun createPartnerToken(partner: PartnerResponse): TokenResponse =
        TokenResponse(
            access = generateToken(
                id = partner.id,
                role = AccessRole.PARTNER,
                tokenType = TokenType.ACCESS,
                expirationDate = 60 * 60 * 1000L // 1 hours
            ),
            refresh = generateToken(
                id = partner.id,
                role = AccessRole.PARTNER,
                tokenType = TokenType.REFRESH,
                expirationDate = 90 * 24 * 60 * 60 * 1000L // 90 day
            )
        )

    private fun generateToken(id: String, role: AccessRole, tokenType: TokenType, expirationDate: Long): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("userId", id)
            .withClaim("role", role.name)
            .withClaim("token_type", tokenType.value)
            .withExpiresAt(Date(System.currentTimeMillis() + expirationDate))
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyToken(tokenType: TokenType): JWTVerifier =
        JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("token_type", tokenType.value)
            .build()

    fun customValidator(credential: JWTCredential): JWTPrincipal? {
        return if (audienceMatches(credential) && !isTokenExpired(credential)) {
            JWTPrincipal(credential.payload)
        } else {
            null
        }
    }

    private fun isTokenExpired(credential: JWTCredential): Boolean {
        val expiresAt = credential.payload.expiresAt
        return expiresAt?.before(Date()) ?: true
    }

    private fun audienceMatches(credential: JWTCredential): Boolean =
        credential.payload.audience.contains(audience)

    private fun getConfigProperty(path: String): String =
        application.environment.config.property(path).getString()
}