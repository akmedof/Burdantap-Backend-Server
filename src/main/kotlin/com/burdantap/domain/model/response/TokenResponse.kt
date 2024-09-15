package com.burdantap.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val access: String,
    val refresh: String,
)
