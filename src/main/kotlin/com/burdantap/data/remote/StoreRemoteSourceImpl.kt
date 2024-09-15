package com.burdantap.data.remote

import com.burdantap.domain.dto.StoreDto
import com.burdantap.domain.entity.StoreEntity
import com.burdantap.domain.mapper.toCreateEntity
import com.burdantap.domain.mapper.toResponse
import com.burdantap.domain.model.response.StoreResponse
import com.burdantap.domain.reposirory.StoreRemoteSource
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class StoreRemoteSourceImpl(
    private val database: CoroutineDatabase
): StoreRemoteSource {

    private val storeCollection = database.getCollection<StoreEntity>("store")

    override suspend fun create(dto: StoreDto, partnerId: String): Boolean {
       return storeCollection.insertOne(document = dto.toCreateEntity(partnerId)).wasAcknowledged()
    }

    override suspend fun read(partnerId: String): StoreResponse? {
        return storeCollection.find(filter = StoreEntity::partnerId eq partnerId).first()?.toResponse()
    }

}