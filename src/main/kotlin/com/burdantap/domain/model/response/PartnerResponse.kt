package com.burdantap.domain.model.response

import com.burdantap.domain.model.type.Verification
import kotlinx.serialization.Serializable

@Serializable
data class PartnerResponse(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val storeStatus: Verification
)
