package com.burdantap.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    val id: String,
    val name: String,
    val slug: String,
)
