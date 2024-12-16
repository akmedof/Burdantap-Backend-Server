package com.burdantap.domain.model.dto.partner

import kotlinx.serialization.Serializable

@Serializable
data class PartnerLoginDto(
    val email: String,
    val password: String
)
