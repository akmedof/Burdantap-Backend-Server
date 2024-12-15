package com.burdantap.domain.model.response

import com.burdantap.domain.dto.partner.PartnerDto
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val partner: PartnerResponse,
    val token: TokenResponse
)
