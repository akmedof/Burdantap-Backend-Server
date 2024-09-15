package com.burdantap.domain.reposirory

import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.model.response.product.ProductResponse

interface ProductRemoteSource {
    suspend fun create(dto: ProductDto): Boolean
    suspend fun updateProductDetailsIdByModelCode(modelCode: String): Boolean
    suspend fun readBySlug(slug: String): ProductResponse?
}