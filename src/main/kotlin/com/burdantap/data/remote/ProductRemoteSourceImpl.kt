package com.burdantap.data.remote

import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.entity.product.ProductDetailEntity
import com.burdantap.domain.entity.product.ProductEntity
import com.burdantap.domain.mapper.toEntity
import com.burdantap.domain.model.response.product.ProductResponse
import com.burdantap.domain.reposirory.ProductRemoteSource
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.document
import org.litote.kmongo.eq
import org.litote.kmongo.setValue

class ProductRemoteSourceImpl(private val database: CoroutineDatabase) : ProductRemoteSource {

    private val productCollection = database.getCollection<ProductEntity>("products")
    private val productDetailCollection = database.getCollection<ProductDetailEntity>("product_details")

    override suspend fun create(dto: ProductDto): Boolean {
        return productCollection.insertOne(document = dto.toEntity()).wasAcknowledged()
    }

    override suspend fun updateProductDetailsIdByModelCode(modelCode: String): Boolean {
        val productDetails = productDetailCollection.find(ProductDetailEntity::modelCode eq modelCode).toList()
        return productCollection.updateOne(
            filter = ProductEntity::modelCode eq modelCode,
            update = setValue(ProductEntity::detailsId, productDetails.map { it.uuid })
        ).wasAcknowledged()
    }

    override suspend fun readBySlug(slug: String): ProductResponse? {
        TODO("Not yet implemented")
    }
}