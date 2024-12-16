package com.burdantap.controller.category

import com.burdantap.data.repository.CategoryRepository
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.categoryRoutes() {
    val repository: CategoryRepository by inject(CategoryRepository::class.java)
    categoryCreateRoute(repository)
    subcategoryCreateRoute(repository)
    subSubcategoryCreateRoute(repository)
    readCategoryRoute(repository)
    readMainCategoryRoute(repository)
    readSubCategoryRoute(repository)
}
