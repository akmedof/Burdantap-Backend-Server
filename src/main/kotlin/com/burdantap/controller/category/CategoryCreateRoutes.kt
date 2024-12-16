package com.burdantap.controller.category

import com.burdantap.controller.endpoint.CategoryEndpoint
import com.burdantap.data.repository.CategoryRepository
import com.burdantap.domain.model.base.BaseResponse
import com.burdantap.domain.model.dto.category.CategoryDto
import com.burdantap.domain.model.dto.category.SubSubcategoryDto
import com.burdantap.domain.model.dto.category.SubcategoryDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.categoryCreateRoute(repository: CategoryRepository) {
    post(CategoryEndpoint.CreateCategory.path) {
        val dto = call.receive<CategoryDto>()
        val isCreate = repository.createCategory(dto)
        call.respond(HttpStatusCode.Created, message = BaseResponse(
            success = isCreate,
            data = if (isCreate) "created" else "not created",
        ))
    }
}

fun Route.subcategoryCreateRoute(repository: CategoryRepository) {
    post(CategoryEndpoint.CreateSubcategory.path) {
        val dto = call.receive<SubcategoryDto>()
        val isCreate = repository.createSubcategory(dto)
        call.respond(HttpStatusCode.Created, message = BaseResponse(
            success = isCreate,
            data = if (isCreate) "created" else "not created",
        ))
    }
}

fun Route.subSubcategoryCreateRoute(repository: CategoryRepository) {
    post(CategoryEndpoint.CreateSubSubcategory.path) {
        val dto = call.receive<SubSubcategoryDto>()
        val isCreate = repository.createSubSubcategory(dto)
        call.respond(HttpStatusCode.Created, message = BaseResponse(
            success = isCreate,
            data = if (isCreate) "created" else "not created",
        ))
    }
}