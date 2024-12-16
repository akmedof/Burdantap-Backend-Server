package com.burdantap.domain.mapper

import com.burdantap.domain.model.dto.StoreDto
import com.burdantap.domain.model.entity.StoreEntity
import com.burdantap.domain.model.response.StoreResponse
import com.burdantap.util.toSlug

fun StoreDto.toCreateEntity(partnerId: String) = StoreEntity(
    name = name,
    slug = name.toSlug(),
    partnerId = partnerId,
)

fun StoreEntity.toResponse() = StoreResponse(
    id = uuid,
    name = name,
    slug = slug,
)