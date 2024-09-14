package com.burdantap.domain.model.response

data class TokenResponse(
    val access: String,
    val refresh: String,
)
