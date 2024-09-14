package com.burdantap.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PartnerResponse(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
)
