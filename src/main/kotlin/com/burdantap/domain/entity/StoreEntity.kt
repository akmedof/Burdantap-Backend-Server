package com.burdantap.domain.entity

import com.burdantap.domain.model.type.Verification
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class StoreEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val slug: String,
    val phone: String = "",
    val cityId: String = "",
    val regionId: String = "",
    val address: String = "",
    val categoryId: String = "",
    val profilePhoto: String = "",
    val coverPhoto: String = "",
    val verification: Verification = Verification.NOT_VERIFIED,
    val partnerId: String,
    val createdAt: String = LocalDateTime.now().toString()
)
