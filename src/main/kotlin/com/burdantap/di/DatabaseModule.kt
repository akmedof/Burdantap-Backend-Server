package com.burdantap.di

import com.burdantap.data.remote.*
import com.burdantap.data.repository.*
import com.burdantap.domain.reposirory.*
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val databaseModule = module {
    single { KMongo.createClient().coroutine.getDatabase("burdantap_database") }

    single<PartnerRemoteSource> { PartnerRemoteSourceImpl(get()) }
    single<PartnerRepository> { PartnerRepository(get()) }

    single<StoreRemoteSource> { StoreRemoteSourceImpl(get()) }
    single<StoreRepository> { StoreRepository(get()) }

    single<ProductRemoteSource> { ProductRemoteSourceImpl(get()) }
    single<ProductRepository> { ProductRepository(get()) }

    single<ProductDetailRemoteSource> { ProductDetailRemoteSourceImpl(get()) }
    single<ProductDetailRepository> { ProductDetailRepository(get()) }

    single<ColorRemoteSource> { ColorRemoteSourceImpl(get()) }
    single<ColorRepository> { ColorRepository(get()) }
}