package com.burdantap.domain.reposirory

import com.burdantap.domain.dto.partner.PartnerDto
import com.burdantap.domain.dto.partner.PartnerLoginDto
import com.burdantap.domain.model.response.PartnerResponse

interface PartnerRemoteSource {

    suspend fun create(partner: PartnerDto): PartnerResponse?
    suspend fun checkEmail(email: String): Boolean
    suspend fun checkEmailAndPassword(loginDto: PartnerLoginDto): PartnerResponse?
    suspend fun readById(id: String): PartnerResponse?

}