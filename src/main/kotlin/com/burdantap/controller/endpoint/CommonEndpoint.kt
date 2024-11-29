package com.burdantap.controller.endpoint

sealed class CommonEndpoint(val route: String) {
    data object CheckEmail : CommonEndpoint("/common/check/{email}")
}