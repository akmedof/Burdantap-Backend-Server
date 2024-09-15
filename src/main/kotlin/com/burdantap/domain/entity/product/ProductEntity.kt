package com.burdantap.domain.entity.product

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class ProductEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val modelCode: String,
    val storeId: String,
    val detailsId: List<String>,
//    val brandId: String,
//    val features: List<ProductFeatureDto>,
    val descriptions: String,
    val createdAt: String = LocalDateTime.now().toString()
)
