package com.burdantap.domain.model.dto.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val modelCode: String,
    val storeId: String,
    val storeSlug: String,
    val description: String,
    val details: List<ProductDetailDto>,
)
