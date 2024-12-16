package com.burdantap.domain.model.entity.category

import com.burdantap.domain.model.response.category.SubSubcategoryResponse
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class SubSubcategoryEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val slug: String,
    val photo: String = "",
    val subcategoriesId: List<String>,
    val createdAt: String = LocalDateTime.now().toString()
){
    fun toResponse(): SubSubcategoryResponse = SubSubcategoryResponse(
        id = uuid,
        name = name,
        slug = slug,
    )
}