package com.burdantap.domain.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val success: Boolean = true,
    val data: T
)
