package com.burdantap.data.remote

import com.burdantap.domain.dto.color.ColorDto
import com.burdantap.domain.entity.ColorEntity
import com.burdantap.domain.mapper.toEntity
import com.burdantap.domain.mapper.toResponse
import com.burdantap.domain.model.response.ColorResponse
import com.burdantap.domain.reposirory.ColorRemoteSource
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ColorRemoteSourceImpl(
    private val database: CoroutineDatabase
): ColorRemoteSource {

    private val colorCollection = database.getCollection<ColorEntity>("colors")

    override suspend fun create(dto: ColorDto): Boolean {
        return colorCollection.insertOne(document = dto.toEntity()).wasAcknowledged()
    }

    override suspend fun readById(id: String): ColorResponse? {
        return colorCollection.findOne(filter = ColorEntity::uuid eq id)?.toResponse()
    }
}