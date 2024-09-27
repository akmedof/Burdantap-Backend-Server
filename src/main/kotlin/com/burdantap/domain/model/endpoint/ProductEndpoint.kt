package com.burdantap.domain.model.endpoint

sealed class ProductEndpoint(val path: String) {
    data object Create : ProductEndpoint("/product/create")
    data object CreateNew : ProductEndpoint("/product/create-new")
    data object CreateTest : ProductEndpoint("/product/create-test")
    data object Read: ProductEndpoint("/{store-slug}/{product-slug}")
}