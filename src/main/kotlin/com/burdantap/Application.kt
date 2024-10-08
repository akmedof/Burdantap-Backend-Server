package com.burdantap

import com.burdantap.controller.configureRouting
import com.burdantap.di.databaseModule
import com.burdantap.plugins.*
import com.burdantap.security.JWTManager
import com.burdantap.security.configureSecurity
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSerialization()
    configureSecurity(JWTManager(this))
    configureRouting()

}

fun Application.configureKoin() {
    install(Koin) {
        modules(databaseModule)
    }
}