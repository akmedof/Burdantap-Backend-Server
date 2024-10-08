package com.burdantap.data.remote

import com.burdantap.domain.dto.partner.PartnerDto
import com.burdantap.domain.dto.partner.PartnerLoginDto
import com.burdantap.domain.entity.PartnerEntity
import com.burdantap.domain.mapper.toEntity
import com.burdantap.domain.mapper.toResponse
import com.burdantap.domain.model.response.PartnerResponse
import com.burdantap.domain.reposirory.PartnerRemoteSource
import com.burdantap.security.hasing.SHA256HashingService
import org.bson.Document
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PartnerRemoteSourceImpl(
    private val database: CoroutineDatabase
): PartnerRemoteSource {

    private val partnerCollection = database.getCollection<PartnerEntity>("partner")

    override suspend fun create(partner: PartnerDto): PartnerResponse? {
        partnerCollection.insertOne(document = partner.toEntity()).wasAcknowledged()
        return partnerCollection.find(PartnerEntity::email eq partner.email).first()?.toResponse()
    }

    override suspend fun checkEmail(email: String): Boolean {
        return partnerCollection.countDocuments(PartnerEntity::email eq email) > 0
    }

    override suspend fun checkEmailAndPassword(loginDto: PartnerLoginDto): PartnerResponse? {
        return partnerCollection.find(
            and(
                PartnerEntity::email eq loginDto.email,
                PartnerEntity::password eq SHA256HashingService().generateSaltedHash(loginDto.password).value
            )
        ).first()?.toResponse()
    }

    override suspend fun readById(id: String): PartnerResponse? {
        return partnerCollection.find(PartnerEntity::uuid eq id).first()?.toResponse()
    }
}