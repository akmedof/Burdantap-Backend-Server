package com.burdantap.domain.model.dto.category

import com.burdantap.domain.model.entity.category.SubSubcategoryEntity
import com.burdantap.util.toSlug
import kotlinx.serialization.Serializable

@Serializable
data class SubSubcategoryDto(
    val name: String,
    val subCategoriesId: List<String>
){
    fun toEntity(): SubSubcategoryEntity = SubSubcategoryEntity(
        name = name,
        slug = name.toSlug(),
        subcategoriesId = subCategoriesId
    )
}
