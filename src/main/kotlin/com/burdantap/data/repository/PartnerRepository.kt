package com.burdantap.data.repository

import com.burdantap.domain.dto.partner.PartnerDto
import com.burdantap.domain.dto.partner.PartnerLoginDto
import com.burdantap.domain.model.response.PartnerResponse
import com.burdantap.domain.reposirory.PartnerRemoteSource

class PartnerRepository(
    private val remote: PartnerRemoteSource
): PartnerRemoteSource {
    override suspend fun create(partner: PartnerDto): PartnerResponse? {
        return remote.create(partner)
    }

    override suspend fun checkEmail(email: String): Boolean {
        return remote.checkEmail(email)
    }

    override suspend fun checkEmailAndPassword(loginDto: PartnerLoginDto): PartnerResponse? {
        return remote.checkEmailAndPassword(loginDto)
    }

    override suspend fun readById(id: String): PartnerResponse? {
        return remote.readById(id)
    }

//    override suspend fun getPartnerById(id: String): PartnerResponse {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun existsPartnerByEmail(email: String): PartnerResponse {
//        TODO("Not yet implemented")
//    }
}