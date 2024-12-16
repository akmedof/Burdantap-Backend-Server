package com.burdantap.domain.reposirory

import com.burdantap.domain.model.dto.color.ColorDto
import com.burdantap.domain.model.response.ColorResponse


interface ColorRemoteSource {

    suspend fun create(dto: ColorDto): Boolean
    suspend fun readById(id: String): ColorResponse?

}