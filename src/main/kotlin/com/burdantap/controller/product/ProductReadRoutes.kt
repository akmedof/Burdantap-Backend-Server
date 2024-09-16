package com.burdantap.controller.product

import com.burdantap.data.repository.ProductRepository
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.ProductEndpoint
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.productReadRoutes() {
    val repository by inject<ProductRepository>()
    readBySlug(repository)
}

private fun Route.readBySlug(repository: ProductRepository) {
    get(ProductEndpoint.Read.path) {
        val storeSlug = call.parameters["store-slug"] ?: ""
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