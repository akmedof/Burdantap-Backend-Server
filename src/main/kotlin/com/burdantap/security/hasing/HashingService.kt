package com.burdantap.security.hasing

import com.burdantap.domain.model.securty.Hashing

interface HashingService {
    fun generateSaltedHash(value: String): Hashing
    fun verify(value: String, hashingPassword: String): Boolean
}