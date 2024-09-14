package com.burdantap.domain.mapper

import com.burdantap.domain.dto.partner.PartnerDto
import com.burdantap.domain.entity.PartnerEntity
import com.burdantap.domain.model.response.PartnerResponse
import com.burdantap.security.hasing.SHA256HashingService

fun PartnerEntity.toResponse(): PartnerResponse =
    PartnerResponse(
        id = this.uuid,
        name = this.name,
        surname = this.surname,
        email = this.email,
    )

fun PartnerDto.toEntity(): PartnerEntity =
    PartnerEntity(
        name = this.name,
        surname = this.surname,
        email = this.email,
        password = SHA256HashingService().generateSaltedHash(password).value
    )