package com.burdantap.controller.product

import com.burdantap.data.repository.ProductDetailRepository
import com.burdantap.data.repository.ProductRepository
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.ProductEndpoint
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.productReadRoutes() {
    val repository by inject<ProductRepository>()
    val detailRepository by inject<ProductDetailRepository>()
    readBySlug(repository)
    readProductDetailsByModelCode(detailRepository)
}

private fun Route.readBySlug(repository: ProductRepository) {
    get(ProductEndpoint.Read.path) {
        val productSlug = call.parameters["product-slug"] ?: ""
        val product = repository.readBySlug(productSlug)
        call.respond(
            message = BaseResponse(
                success = true,
                data = product
            ),
            status = HttpStatusCode.OK
        )
    }
}

private fun Route.readProductDetailsByModelCode(productDetailRepository: ProductDetailRepository){
    get(ProductEndpoint.ReadProductDetailsByModelCode.path) {
        val modelCode = call.parameters["model-code"] ?: ""
        val productDetails = productDetailRepository.readDetailsByModelCode(modelCode)
        call.respond(
            message = BaseResponse(
                success = true,
                data = productDetails
            ),
            status = HttpStatusCode.OK
        )
    }
}