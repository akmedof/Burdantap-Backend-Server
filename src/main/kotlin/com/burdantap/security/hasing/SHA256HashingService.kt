package com.burdantap.security.hasing

import com.burdantap.domain.model.securty.Hashing
import org.apache.commons.codec.digest.DigestUtils

class SHA256HashingService: HashingService {
    override fun generateSaltedHash(value: String): Hashing {
        return Hashing(value = DigestUtils.sha256Hex(value))
    }

    override fun verify(value: String, hashingPassword: String): Boolean {
        return value == DigestUtils.sha256Hex(hashingPassword)
    }
}