package com.burdantap.domain.model.endpoint

sealed class FileEndpoint(val path: String) {
    data object ReadProductImage: FileEndpoint("/gallery/product/{store-slug}/{model-code}/{color-slug}/{image-name}")
}
