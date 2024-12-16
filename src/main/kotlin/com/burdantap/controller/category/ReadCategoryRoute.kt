package com.burdantap.controller.category

import com.burdantap.controller.endpoint.CategoryEndpoint
import com.burdantap.data.repository.CategoryRepository
import com.burdantap.domain.model.base.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.readCategoryRoute(repository: CategoryRepository){
    get(CategoryEndpoint.ReadCategory.path){
        call.respond(
            message = BaseResponse(
                data = repository.readCategories()
            )
        )
    }
}

fun Route.readMainCategoryRoute(repository: CategoryRepository){
    get(CategoryEndpoint.ReadMainCategory.path){
        call.respond(
            message = BaseResponse(
                data = repository.readMainCategories()
            )
        )
    }
}

fun Route.readSubCategoryRoute(repository: CategoryRepository){
    get(CategoryEndpoint.ReadSubCategory.path){
        call.respond(
            message = BaseResponse(
                data = repository.readSubCategories()
            )
        )
    }
}