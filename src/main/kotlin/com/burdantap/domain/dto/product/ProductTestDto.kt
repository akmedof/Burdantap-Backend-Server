package com.burdantap.domain.dto.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductTestDto(
    val modelCode: String = "",
    val storeId: String = "",
    val details: List<ProductDetailTestDto> = listOf(),
    val description: String = "",
)
