package com.burdantap.domain.dto.partner

import kotlinx.serialization.Serializable

@Serializable
data class PartnerLoginDto(
    val email: String,
    val password: String
)
