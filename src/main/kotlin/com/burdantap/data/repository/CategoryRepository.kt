package com.burdantap.data.repository

import com.burdantap.domain.model.dto.category.CategoryDto
import com.burdantap.domain.model.dto.category.SubSubcategoryDto
import com.burdantap.domain.model.dto.category.SubcategoryDto
import com.burdantap.domain.model.response.category.CategoryResponse
import com.burdantap.domain.model.response.category.MainCategoryResponse
import com.burdantap.domain.model.response.category.SubcategoryResponse
import com.burdantap.domain.reposirory.CategoryRemoteSource

class CategoryRepository(private val remote: CategoryRemoteSource): CategoryRemoteSource {
    override suspend fun createCategory(category: CategoryDto): Boolean {
        return remote.createCategory(category)
    }

    override suspend fun createSubcategory(subcategory: SubcategoryDto): Boolean {
        return remote.createSubcategory(subcategory)
    }

    override suspend fun createSubSubcategory(subSubcategory: SubSubcategoryDto): Boolean {
        return remote.createSubSubcategory(subSubcategory)
    }

    override suspend fun updateSubSubcategory(subSubcategoryId: String, newSubcategoriesId: List<String>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun readCategories(): List<CategoryResponse> {
        return remote.readCategories()
    }

    override suspend fun readMainCategories(): List<MainCategoryResponse> {
        return remote.readMainCategories()
    }

    override suspend fun readSubCategories(): List<SubcategoryResponse> {
        return remote.readSubCategories()
    }
}