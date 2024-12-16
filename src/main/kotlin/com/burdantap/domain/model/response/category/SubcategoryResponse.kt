package com.burdantap.domain.model.response.category

import kotlinx.serialization.Serializable

@Serializable
data class SubcategoryResponse(
    val id: String,
    val name: String,
    val slug: String,
    val subSubcategory: List<SubSubcategoryResponse>
)