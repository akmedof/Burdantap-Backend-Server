package com.burdantap.data.repository

import com.burdantap.domain.dto.product.ProductDetailDto
import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.model.response.product.ProductResponse
import com.burdantap.domain.reposirory.ProductDetailRemoteSource
import com.burdantap.domain.reposirory.ProductRemoteSource

class ProductDetailRepository(private val remote: ProductDetailRemoteSource): ProductDetailRemoteSource {
    override suspend fun create(dtoCollection: List<ProductDetailDto>, modelCode: String): Boolean {
        return remote.create(dtoCollection, modelCode)
    }


}