package com.burdantap.domain.dto.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailTestDto(
    val title: String,
    val color: String,
    val images: List<String>,
)
