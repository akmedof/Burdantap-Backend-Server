package com.burdantap.domain.mapper

import com.burdantap.domain.dto.color.ColorDto
import com.burdantap.domain.entity.ColorEntity
import com.burdantap.domain.model.response.ColorResponse
import com.burdantap.util.toSlug

fun ColorDto.toEntity(): ColorEntity = ColorEntity(
    name = name,
    slug = name.toSlug()
)

fun ColorEntity.toResponse(): ColorResponse = ColorResponse(
    id = uuid,
    name = name,
    slug = slug.toSlug(),
)