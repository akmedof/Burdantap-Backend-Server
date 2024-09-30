package com.burdantap.domain.mapper

import com.burdantap.domain.dto.product.ProductDetailDto
import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.entity.product.ProductDetailEntity
import com.burdantap.domain.entity.product.ProductEntity
import com.burdantap.domain.model.response.product.ProductDetailResponse
import com.burdantap.util.Constants
import com.burdantap.util.generateNineDigitNumber
import com.burdantap.util.normalizeProductTitle
import com.burdantap.util.toSlug

fun ProductDto.toEntity() = ProductEntity(
    modelCode = modelCode,
    storeId = storeId,
    descriptions = description
)

fun ProductDetailEntity.toResponse(): ProductDetailResponse = ProductDetailResponse(
    id = uuid,
    modelCode = modelCode,
    title = title,
    slug = slug,
    images = imageUrls.map { "${Constants.BASE_URL}/$it" },
)

fun List<ProductDetailDto>.toEntitiesMap(modelCode: String, imageMap: Map<String, List<String>>): List<ProductDetailEntity> {
    return this.map { it.toEntity(modelCode = modelCode, images = imageMap[it.colorSlug] ?: listOf()) }
}

fun ProductDetailDto.toEntity(modelCode: String, images: List<String>) = ProductDetailEntity(
    title = title.normalizeProductTitle(),
    slug = "${title.normalizeProductTitle().toSlug()}-${generateNineDigitNumber()}",
    modelCode = modelCode,
    imageUrls = images,
)

fun List<ProductDetailEntity>.toResponses(): List<ProductDetailResponse> {
    return this.map { it.toResponse() }
}