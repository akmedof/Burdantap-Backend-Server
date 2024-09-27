package com.burdantap.controller.color

import com.burdantap.data.repository.ColorRepository
import com.burdantap.domain.dto.color.ColorDto
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.endpoint.ColorEndpoint
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.colorRoutes() {
    val repository: ColorRepository by inject()
    createColorRoute(repository)
}

private fun Route.createColorRoute(repository: ColorRepository) {
    post(ColorEndpoint.Create.path) {
        val dto = call.receive<ColorDto>()
        val isCreated = repository.create(dto)
        call.respond(
            message = BaseResponse(
                data = if (isCreated) "CREATED" else "NOT FOUND",
            ),
            status = if (isCreated) HttpStatusCode.Created else HttpStatusCode.OK
        )
    }
}