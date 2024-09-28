package com.burdantap.domain.model.response.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailResponse(
    val id: String,
    val modelCode: String,
    val title: String,
    val slug: String,
    val images: List<String>,
)
