package com.burdantap.domain.model.endpoint

sealed class AuthEndpoint(val path: String) {
    data object CustomerLogin : AuthEndpoint("/auth/login/customer")
    data object CustomerRegister : AuthEndpoint("/auth/register/customer")
    data object CustomerVerified : AuthEndpoint("/auth/verify/customer")
    data object PartnerLogin : AuthEndpoint("/auth/login/partner")
    data object PartnerRegister : AuthEndpoint("/auth/register/partner")
    data object PartnerVerified : AuthEndpoint("/auth/verify/partner")
    data object Unauthorized : AuthEndpoint("/auth/unauthorized")
}