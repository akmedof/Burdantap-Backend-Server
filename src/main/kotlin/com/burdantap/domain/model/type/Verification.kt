package com.burdantap.domain.model.type

import kotlinx.serialization.Serializable

@Serializable
enum class Verification {
    NOT_VERIFIED, VERIFIED,
    STORE_NOT_CREATED, STORE_CREATED
}