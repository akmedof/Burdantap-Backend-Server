package com.burdantap.controller.endpoint

sealed class ColorEndpoint(val path: String) {
    data object Create: ColorEndpoint("/color/create")
    data object Read: ColorEndpoint("/color/read")
}