package com.burdantap.controller.product

import com.burdantap.data.repository.ProductDetailRepository
import com.burdantap.data.repository.ProductRepository
import com.burdantap.data.repository.StoreRepository
import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.FileDirectionPath
import com.burdantap.domain.model.endpoint.ProductEndpoint
import com.burdantap.util.createProductImagePath
import com.burdantap.util.fileProductDirectionCreate
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import java.io.File

@ExperimentalSerializationApi
private val json = Json {
    ignoreUnknownKeys = true
    explicitNulls = true
}

@ExperimentalSerializationApi
fun Route.productCreateRouteNew() {
    val repository by inject<ProductRepository>()
    val detailRepository by inject<ProductDetailRepository>()
    var product: ProductDto? = null
    val mapImages: MutableMap<String, MutableList<String>> = mutableMapOf()
    post(ProductEndpoint.CreateNew.path) {
        call.receiveMultipart().forEachPart { part ->
            if (part is PartData.FileItem) {
                when (part.contentType) {
                    ContentType.Application.Json -> {
                        part.provider().use { input ->
                            val productJson = input.readBytes().toString(Charsets.UTF_8)
                            product = json.decodeFromString<ProductDto>(productJson)
                        }
                    }
                    ContentType.Image.JPEG, ContentType.Image.PNG -> {
                        if (product != null) {
                            product?.details?.forEach { item ->
                                if (item.colorSlug == part.name) {
                                    val targetDir = fileProductDirectionCreate(
                                        storeSlug = product?.storeSlug.toString(),
                                        modelCode = product?.modelCode.toString(),
                                        colorSlug = item.colorSlug,
                                    )
                                    part.provider().use { input ->
                                        val byteArray = input.readBytes()
                                        File(targetDir, part.originalFileName ?: "Empty").writeBytes(byteArray)
                                    }
                                    val url =
                                        "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}/${
                                            createProductImagePath(
                                                storeSlug = product?.storeSlug.toString(),
                                                modelCode = product?.modelCode.toString(),
                                                colorSlug = item.colorSlug,
                                                imageName = part.originalFileName.toString()
                                            )
                                        }"
                                    mapImages.computeIfAbsent(item.colorSlug) { mutableListOf() }.add(url)
                                }
                            }
                        }
                    }
                }
            }
            part.dispose()
        }
        if (product != null && product?.details?.isNotEmpty() == true) {
            detailRepository.create(
                modelCode = product?.modelCode.toString(),
                imageMap = mapImages,
                dtoCollection = product?.details ?: listOf(),
            )
        }
        application.log.info("IMAGES: ${mapImages[product?.details?.get(0)?.colorSlug]}")
        call.respond(
            message = BaseResponse(
                success = true,
                data = product,
            ),
            status = HttpStatusCode.Created,
        )
    }
}