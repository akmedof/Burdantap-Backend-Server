package com.burdantap.domain.model.endpoint

sealed class StoreEndpoint(val path: String) {
    data object Create: StoreEndpoint("/store/create")
    data object Read: StoreEndpoint("/store/read")
    data object Update: StoreEndpoint("/store/update")
    data object Delete: StoreEndpoint("/store/delete")
    data object Check: StoreEndpoint("/store/check")

}