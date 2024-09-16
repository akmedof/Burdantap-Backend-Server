package com.burdantap.data.repository

import com.burdantap.domain.dto.StoreDto
import com.burdantap.domain.model.response.StoreResponse
import com.burdantap.domain.reposirory.StoreRemoteSource

class StoreRepository(private val remote: StoreRemoteSource): StoreRemoteSource {
    override suspend fun create(dto: StoreDto, partnerId: String): Boolean {
        return remote.create(dto, partnerId)
    }

    override suspend fun read(partnerId: String): StoreResponse? {
        return remote.read(partnerId)
    }

    override suspend fun existsStoreBySlug(slug: String): Boolean {
        return remote.existsStoreBySlug(slug)
    }

}