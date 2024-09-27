package com.burdantap.domain.dto.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDto(
    val title: String,
    val colorId: String,
    val colorSlug: String,
)
