package com.burdantap.domain.reposirory

import com.burdantap.domain.dto.product.ProductDetailDto
import com.burdantap.domain.model.response.product.ProductDetailResponse

interface ProductDetailRemoteSource {
    suspend fun create(
        modelCode: String,
        imageMap: Map<String, List<String>>,
        dtoCollection: List<ProductDetailDto>
    ): Boolean

    suspend fun readDetailsByModelCode(modelCode: String): List<ProductDetailResponse>
}