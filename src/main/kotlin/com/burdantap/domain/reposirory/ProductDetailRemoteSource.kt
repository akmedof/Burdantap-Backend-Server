package com.burdantap.domain.reposirory

import com.burdantap.domain.dto.product.ProductDetailDto

interface ProductDetailRemoteSource {
    suspend fun create(dtoCollection: List<ProductDetailDto>, modelCode: String): Boolean
}