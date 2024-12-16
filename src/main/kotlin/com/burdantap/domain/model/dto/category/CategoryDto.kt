package com.burdantap.domain.model.dto.category

import com.burdantap.domain.model.entity.category.CategoryEntity
import com.burdantap.util.toSlug
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val name: String,
){
    fun toEntity(): CategoryEntity = CategoryEntity(
        name = name,
        slug = name.toSlug(),
    )
}