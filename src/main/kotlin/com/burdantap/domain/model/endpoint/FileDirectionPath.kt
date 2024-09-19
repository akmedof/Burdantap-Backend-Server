package com.burdantap.domain.model.endpoint

sealed class FileDirectionPath(val path: String) {
    data object Uploads : FileDirectionPath("uploads")
    data object Gallery : FileDirectionPath("/gallery")
    data object Customers : FileDirectionPath("/customers")
    data object Partners : FileDirectionPath("/partners")
    data object Stores : FileDirectionPath("/stores")
    data object Products : FileDirectionPath("/stores")
}