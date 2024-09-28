package com.burdantap.domain.entity.product

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class ProductDetailEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val title: String,
    val slug: String,
    val modelCode: String,
    val imageUrls: List<String> = listOf(),
//    val colorId: String,
//    val specificationsId: List<String>,
    val createdAt: String = LocalDateTime.now().toString()
)
