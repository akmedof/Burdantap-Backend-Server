
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
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import java.io.File
import javax.mail.Multipart
import kotlin.io.use

internal fun Route.productCreateTestRoute() {
    post(ProductEndpoint.CreateTest.path) {
        val multipart = call.receiveMultipart()
        var fileName = ""
        var productDto: ProductTestDto? = null
        var file: File? = null
        multipart.forEachPart { part ->
            if (part is PartData.FileItem) {
                fileName = part.originalFileName ?: "uploadedFile"

                if(fileName.contains(".json")) {
                    file = File("uploads/$fileName")
                    // Dosya içeriğini kaydet
                    part.streamProvider().use { inputStream ->
                        application.log.info("File read text: ${file!!.readText()}")
                        file!!.outputStream().buffered().use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                        productDto = Json.decodeFromString<ProductTestDto>(file!!.readText())
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

