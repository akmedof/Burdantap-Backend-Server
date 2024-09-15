package com.burdantap.domain.dto.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val modelCode: String,
    val storeId: String,
    val details: List<ProductDetailDto>,
    val description: String,
)
