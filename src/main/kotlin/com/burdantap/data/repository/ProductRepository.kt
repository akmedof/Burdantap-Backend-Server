package com.burdantap.data.repository

import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.model.response.product.ProductResponse
import com.burdantap.domain.reposirory.ProductRemoteSource

class ProductRepository(private val remote: ProductRemoteSource): ProductRemoteSource {
    override suspend fun create(dto: ProductDto): Boolean {
        return remote.create(dto)
    }

//    override suspend fun updateProductDetailsIdByModelCode(modelCode: String): Boolean {
//        return remote.updateProductDetailsIdByModelCode(modelCode)
//    }

    override suspend fun readBySlug(slug: String): ProductResponse? {
        return remote.readBySlug(slug)
    }

}