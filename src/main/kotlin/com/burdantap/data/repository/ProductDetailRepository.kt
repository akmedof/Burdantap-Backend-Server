package com.burdantap.data.repository

import com.burdantap.domain.dto.product.ProductDetailDto
import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.model.response.product.ProductDetailResponse
import com.burdantap.domain.model.response.product.ProductResponse
import com.burdantap.domain.reposirory.ProductDetailRemoteSource
import com.burdantap.domain.reposirory.ProductRemoteSource

class ProductDetailRepository(private val remote: ProductDetailRemoteSource): ProductDetailRemoteSource {

    override suspend fun create(
        modelCode: String,
        imageMap: Map<String, List<String>>,
        dtoCollection: List<ProductDetailDto>
    ): Boolean {
        return remote.create(modelCode, imageMap, dtoCollection)
    }

    override suspend fun readDetailsByModelCode(modelCode: String): List<ProductDetailResponse> {
        return remote.readDetailsByModelCode(modelCode)
    }


}