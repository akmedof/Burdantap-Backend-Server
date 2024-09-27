@file:OptIn(ExperimentalSerializationApi::class)

package com.burdantap.controller.product

import com.burdantap.domain.dto.product.ProductTestDto
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.ProductEndpoint
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.io.use

private val json = Json {
    ignoreUnknownKeys = true
    explicitNulls = true
}

internal fun Route.productCreateTestRoute() {
    post(ProductEndpoint.CreateTest.path) {
        val multipart = call.receiveMultipart()
        var fileName = ""
        var productDto: ProductTestDto? = null
        val uploadDirPath = "uploads/store/blue"
        val targetDir = File(uploadDirPath)

        if (!targetDir.exists()) {
            targetDir.mkdirs() // Gerekli dizin yapısını oluştur
        }
        multipart.forEachPart { part ->
            if (part is PartData.FileItem) {
                fileName = part.originalFileName ?: "uploadedFile"
                application.log.info("FILE TYPE: KEY NAME = ${part.name}")
                part.contentType?.let { contentType ->
                    when (contentType) {
                        ContentType.Application.Json -> {
                            application.log.info("FILE TYPE: ${contentType}")
                            application.log.info("FILE NAME: $fileName")
                            part.provider().use { inputStream ->
                                val jsonString = inputStream.readBytes().toString(Charsets.UTF_8) // JSON dosyasını string'e çevir
                                productDto = json.decodeFromString<ProductTestDto>(jsonString) // JSON'u modele çevir
                            }
                        }
                        ContentType.Text.Plain -> {
                            application.log.info("FILE TYPE: ${contentType}")
                            application.log.info("FILE NAME: $fileName")
                        }
                        ContentType.Image.JPEG, ContentType.Image.PNG -> {
                            application.log.info("FILE TYPE: ${contentType}")
                            application.log.info("FILE NAME: $fileName")
                            part.provider().use { inputStream ->
                                val byteArray = inputStream.readBytes() // Görsel dosyasını bayt dizisine çevir
                                File(targetDir, fileName).writeBytes(byteArray) // Bayt dizisini dosyaya yaz
                                application.log.info("Image file uploaded: $fileName")
                            }
                        }
                    }
                }
            }
            part.dispose()
        }
        call.respond(
            message = BaseResponse(
                success = true,
                data = productDto,
            ),
            status = HttpStatusCode.Created,
        )
//        call.respondText("Dosya başarıyla yüklendi: $fileName \n$productDto")
    }
}

