package com.burdantap.domain.reposirory

import com.burdantap.domain.dto.partner.PartnerDto
import com.burdantap.domain.dto.partner.PartnerLoginDto
import com.burdantap.domain.model.response.PartnerResponse

interface PartnerRemoteSource {

    suspend fun create(partner: PartnerDto): Boolean
    suspend fun checkEmailAndPassword(loginDto: PartnerLoginDto): PartnerResponse?
//    suspend fun getPartnerById(id: String): PartnerResponse
//    suspend fun existsPartnerByEmail(email: String): PartnerResponse

}