package com.burdantap.domain.model.endpoint

sealed class ErrorEndpoint(val path: String) {
    data object NotFoundPartner : ErrorEndpoint("/error/not-found-partner")
    data object Unauthorized : ErrorEndpoint("/error/unauthorized")
    data object Forbidden : ErrorEndpoint("/error/forbidden")
}