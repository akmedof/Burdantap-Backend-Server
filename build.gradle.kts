
val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val kMongoVersion: String by project
val koinVersion: String by project

plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"

}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    implementation("commons-codec:commons-codec:1.15")

    //Content Negotiation
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")

    //Auth
    implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")

    //KMongo
    implementation("org.litote.kmongo:kmongo-async:$kMongoVersion")
    implementation("org.mongodb:mongodb-driver-sync:$kMongoVersion")
    implementation("org.litote.kmongo:kmongo-coroutine-serialization:$kMongoVersion")

    //Koin Core features
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")

    //JWT
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")

    //Email
    implementation("com.sun.mail:javax.mail:1.6.2")

    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.3")
}