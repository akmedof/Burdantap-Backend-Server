package com.burdantap.domain.model.dto.category

import com.burdantap.domain.model.entity.category.SubcategoryEntity
import com.burdantap.util.toSlug
import kotlinx.serialization.Serializable

@Serializable
data class SubcategoryDto(
    val name: String,
    val categoryId: String
){
    fun toEntity(): SubcategoryEntity = SubcategoryEntity(
        name = name,
        slug = name.toSlug(),
        categoryId = categoryId
    )
}