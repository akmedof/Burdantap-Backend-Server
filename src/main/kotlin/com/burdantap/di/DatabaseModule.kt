package com.burdantap.di

import com.burdantap.data.remote.PartnerRemoteSourceImpl
import com.burdantap.data.remote.ProductDetailRemoteSourceImpl
import com.burdantap.data.remote.ProductRemoteSourceImpl
import com.burdantap.data.remote.StoreRemoteSourceImpl
import com.burdantap.data.repository.PartnerRepository
import com.burdantap.data.repository.ProductDetailRepository
import com.burdantap.data.repository.ProductRepository
import com.burdantap.data.repository.StoreRepository
import com.burdantap.domain.reposirory.PartnerRemoteSource
import com.burdantap.domain.reposirory.ProductDetailRemoteSource
import com.burdantap.domain.reposirory.ProductRemoteSource
import com.burdantap.domain.reposirory.StoreRemoteSource
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
}