package com.burdantap.domain.reposirory

import com.burdantap.domain.model.dto.category.CategoryDto
import com.burdantap.domain.model.dto.category.SubSubcategoryDto
import com.burdantap.domain.model.dto.category.SubcategoryDto
import com.burdantap.domain.model.response.category.CategoryResponse
import com.burdantap.domain.model.response.category.MainCategoryResponse
import com.burdantap.domain.model.response.category.SubSubcategoryResponse
import com.burdantap.domain.model.response.category.SubcategoryResponse

interface CategoryRemoteSource {
    suspend fun createCategory(category: CategoryDto): Boolean
    suspend fun createSubcategory(subcategory: SubcategoryDto): Boolean
    suspend fun createSubSubcategory(subSubcategory: SubSubcategoryDto): Boolean
    suspend fun updateSubSubcategory(subSubcategoryId: String, newSubcategoriesId: List<String>): Boolean
    suspend fun readCategories(): List<CategoryResponse>
    suspend fun readMainCategories(): List<MainCategoryResponse>
    suspend fun readSubCategories(): List<SubcategoryResponse>
}