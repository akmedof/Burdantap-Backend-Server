package com.burdantap.controller.product

import com.burdantap.data.repository.ProductDetailRepository
import com.burdantap.data.repository.ProductRepository
import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.model.endpoint.ProductEndpoint
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.productCreateRoute() {
    val productRepository by inject<ProductRepository>()
    val productDetailRepository by inject<ProductDetailRepository>()
    post(ProductEndpoint.Create.path) {
        val dto = call.receive<ProductDto>()
        application.log.info("Product created: $dto")
        val isCreateProduct = productRepository.create(dto)
        val isCreateDetails = productDetailRepository.create(dto.details, dto.modelCode)
        if (isCreateProduct && isCreateDetails) {
            val isUpdateProductId = productRepository.updateProductDetailsIdByModelCode(
                modelCode = dto.modelCode,
            )
            call.respond(HttpStatusCode.Created, "Product: $isCreateProduct Details: $isCreateDetails Updated ID: $isUpdateProductId")
        }
    }
}