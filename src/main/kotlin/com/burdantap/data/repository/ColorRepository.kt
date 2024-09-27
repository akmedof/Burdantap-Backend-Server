package com.burdantap.data.repository

import com.burdantap.domain.dto.color.ColorDto
import com.burdantap.domain.model.response.ColorResponse
import com.burdantap.domain.reposirory.ColorRemoteSource

class ColorRepository(
    private val remote: ColorRemoteSource
): ColorRemoteSource {
    override suspend fun create(dto: ColorDto): Boolean {
        return remote.create(dto)
    }

    override suspend fun readById(id: String): ColorResponse? {
        return remote.readById(id)
    }
}