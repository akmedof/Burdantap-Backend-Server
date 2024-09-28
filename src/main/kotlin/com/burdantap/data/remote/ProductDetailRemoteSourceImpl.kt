package com.burdantap.data.remote

import com.burdantap.domain.dto.product.ProductDetailDto
import com.burdantap.domain.entity.product.ProductDetailEntity
import com.burdantap.domain.mapper.toEntitiesMap
import com.burdantap.domain.mapper.toResponses
import com.burdantap.domain.model.response.product.ProductDetailResponse
import com.burdantap.domain.reposirory.ProductDetailRemoteSource
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ProductDetailRemoteSourceImpl(private val database: CoroutineDatabase): ProductDetailRemoteSource {

    private val productDetailCollection = database.getCollection<ProductDetailEntity>("product_details")

    override suspend fun create(
        modelCode: String,
        imageMap: Map<String, List<String>>,
        dtoCollection: List<ProductDetailDto>
    ): Boolean {
        return productDetailCollection.insertMany(dtoCollection.toEntitiesMap(modelCode, imageMap)).wasAcknowledged()
    }

    override suspend fun readDetailsByModelCode(modelCode: String): List<ProductDetailResponse> {
        return productDetailCollection.find(filter = ProductDetailEntity::modelCode eq modelCode).toList().toResponses()
    }
}