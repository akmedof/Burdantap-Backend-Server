package com.burdantap.controller.store

import com.burdantap.data.repository.StoreRepository
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.storeRoutes() {
    val repository: StoreRepository by inject<StoreRepository>()
    createStore(repository)
    readStoreByPartnerToken(repository)
    checkStoreBySlugRoute(repository)
}