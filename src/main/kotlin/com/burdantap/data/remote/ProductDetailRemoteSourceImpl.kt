package com.burdantap.data.remote

import com.burdantap.domain.dto.product.ProductDetailDto
import com.burdantap.domain.entity.product.ProductDetailEntity
import com.burdantap.domain.mapper.toEntitiesMap
import com.burdantap.domain.reposirory.ProductDetailRemoteSource
import org.litote.kmongo.coroutine.CoroutineDatabase

class ProductDetailRemoteSourceImpl(private val database: CoroutineDatabase): ProductDetailRemoteSource {

    private val productDetailCollection = database.getCollection<ProductDetailEntity>("product_details")

    override suspend fun create(dtoCollection: List<ProductDetailDto>, modelCode: String): Boolean {
        return productDetailCollection.insertMany(dtoCollection.toEntitiesMap(modelCode)).wasAcknowledged()
    }
}