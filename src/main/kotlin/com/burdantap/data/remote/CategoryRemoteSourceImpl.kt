package com.burdantap.data.remote

import com.burdantap.domain.model.dto.category.CategoryDto
import com.burdantap.domain.model.dto.category.SubSubcategoryDto
import com.burdantap.domain.model.dto.category.SubcategoryDto
import com.burdantap.domain.model.entity.category.CategoryEntity
import com.burdantap.domain.model.entity.category.SubSubcategoryEntity
import com.burdantap.domain.model.entity.category.SubcategoryEntity
import com.burdantap.domain.model.response.category.CategoryResponse
import com.burdantap.domain.model.response.category.MainCategoryResponse
import com.burdantap.domain.model.response.category.SubSubcategoryResponse
import com.burdantap.domain.model.response.category.SubcategoryResponse
import com.burdantap.domain.reposirory.CategoryRemoteSource
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.`in`

class CategoryRemoteSourceImpl(
    private val database: CoroutineDatabase
) : CategoryRemoteSource {

    private val categoryCollections = database.getCollection<CategoryEntity>("categories")
    private val subcategoryCollections = database.getCollection<SubcategoryEntity>("subcategories")
    private val subSubcategoryCollections = database.getCollection<SubSubcategoryEntity>("sub_subcategories")

    override suspend fun createCategory(category: CategoryDto): Boolean {
        return categoryCollections.insertOne(document = category.toEntity()).wasAcknowledged()
    }

    override suspend fun createSubcategory(subcategory: SubcategoryDto): Boolean {
        return subcategoryCollections.insertOne(document = subcategory.toEntity()).wasAcknowledged()
    }

    override suspend fun createSubSubcategory(subSubcategory: SubSubcategoryDto): Boolean {
        return subSubcategoryCollections.insertOne(document = subSubcategory.toEntity()).wasAcknowledged()
    }

    override suspend fun updateSubSubcategory(subSubcategoryId: String, newSubcategoriesId: List<String>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun readCategories(): List<CategoryResponse> {
        return categoryCollections.find().toList().map { category ->
            category.toResponse(
                subcategories = subcategoryCollections.find(SubcategoryDto::categoryId eq category.uuid).toList()
                    .map { subcategory ->
                        subcategory.toSubResponse(
                            subSubcategories = subSubcategoryCollections.find(SubSubcategoryEntity::subcategoriesId `in` subcategory.uuid)
                                .toList().map { subSubcategory ->
                                    subSubcategory.toResponse()
                                }
                        )
                    }
            )
        }
    }

    override suspend fun readMainCategories(): List<MainCategoryResponse> {
        return categoryCollections.find().toList().map { it.toMainResponse() }
    }

    override suspend fun readSubCategories(): List<SubcategoryResponse> {
        return subcategoryCollections.find().toList().map { it.toResponse() }
    }
}