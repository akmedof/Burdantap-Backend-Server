package com.burdantap.domain.reposirory

import com.burdantap.domain.dto.StoreDto
import com.burdantap.domain.model.response.StoreResponse

interface StoreRemoteSource {

    suspend fun create(dto: StoreDto, partnerId: String): Boolean
    suspend fun read(partnerId: String): StoreResponse?


}