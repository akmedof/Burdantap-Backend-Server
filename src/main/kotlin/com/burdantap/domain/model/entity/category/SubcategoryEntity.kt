package com.burdantap.domain.model.entity.category

import com.burdantap.domain.model.response.category.SubSubcategoryResponse
import com.burdantap.domain.model.response.category.SubcategoryResponse
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class SubcategoryEntity(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val slug: String,
    val photo: String = "",
    val categoryId: String,
    val createdAt: String = LocalDateTime.now().toString()
){
    fun toResponse(): SubcategoryResponse = SubcategoryResponse(
        id = uuid,
        name = name,
        slug = slug,
        subSubcategory = listOf()
    )

    fun toSubResponse(subSubcategories: List<SubSubcategoryResponse>): SubcategoryResponse =  SubcategoryResponse(
        id = uuid,
        name = name,
        slug = slug,
        subSubcategory = subSubcategories
    )
}