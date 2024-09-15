package com.burdantap.domain.model.response.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: String,
    val title: String,
    val slug: String,
    val description: String,
    val details: List<ProductDetailResponse>
)
