package com.burdantap.domain.entity

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class PartnerEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val surname: String,
    val email: String,
    val phone: String = "",
    val storeId: String = "",
    val password: String,
    val createdAt: String = LocalDateTime.now().toString()
)
