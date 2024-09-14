
package com.burdantap.domain.dto.partner

import kotlinx.serialization.Serializable


@Serializable
data class PartnerDto(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
)
