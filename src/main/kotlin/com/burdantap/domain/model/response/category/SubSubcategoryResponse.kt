package com.burdantap.domain.model.response.category

import kotlinx.serialization.Serializable

@Serializable
data class SubSubcategoryResponse(
    val id: String,
    val name: String,
    val slug: String,
)