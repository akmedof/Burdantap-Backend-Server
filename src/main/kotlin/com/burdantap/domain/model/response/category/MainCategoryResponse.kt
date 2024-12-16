package com.burdantap.domain.model.response.category

import kotlinx.serialization.Serializable

@Serializable
data class MainCategoryResponse(
    val id: String,
    val name: String,
)