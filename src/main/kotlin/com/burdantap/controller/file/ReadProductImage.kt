package com.burdantap.controller.file

import com.burdantap.domain.model.endpoint.FileEndpoint
import com.burdantap.util.getProductImagePath
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.readProductImage() {
    get(FileEndpoint.ReadProductImage.path) {
        val storeSlug = call.parameters["store-slug"]
        val modelCode = call.parameters["model-code"]
        val colorSlug = call.parameters["color-slug"]
        val imageName = call.parameters["image-name"]
        if (storeSlug != null && modelCode != null && colorSlug != null && imageName != null) {
            call.respondFile(getProductImagePath(storeSlug,modelCode,colorSlug,imageName))
        }else call.respond(HttpStatusCode.NoContent)
    }
}