package com.burdantap.domain.model.entity.category

import com.burdantap.domain.model.response.category.CategoryResponse
import com.burdantap.domain.model.response.category.MainCategoryResponse
import com.burdantap.domain.model.response.category.SubcategoryResponse
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*


@Serializable
data class CategoryEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val slug: String,
    val photo: String = "",
    val createdAt: String = LocalDateTime.now().toString()
){
    fun toResponse(subcategories: List<SubcategoryResponse>): CategoryResponse =  CategoryResponse(
        id = uuid,
        name = name,
        slug = slug,
        subcategories = subcategories
    )

    fun toMainResponse(): MainCategoryResponse =  MainCategoryResponse(
        id = uuid,
        name = name,
    )
}