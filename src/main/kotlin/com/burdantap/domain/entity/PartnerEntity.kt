package com.burdantap.domain.entity

import com.burdantap.domain.model.type.Verification
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class PartnerEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val surname: String,
    val email: String,
    val phone: String = "000-0000-0000",
    val storeId: String = "",
    val storeStatus: Verification = Verification.STORE_NOT_CREATED,
    val password: String,
    val createdAt: String = LocalDateTime.now().toString()
)
