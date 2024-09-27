package com.burdantap.domain.entity

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class ColorEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val slug: String,
    val createdAt: String = LocalDateTime.now().toString()
)
