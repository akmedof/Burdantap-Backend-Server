package com.burdantap.domain.mapper

import com.burdantap.domain.dto.product.ProductDetailDto
import com.burdantap.domain.dto.product.ProductDto
import com.burdantap.domain.entity.product.ProductDetailEntity
import com.burdantap.domain.entity.product.ProductEntity
import com.burdantap.domain.model.response.product.ProductDetailResponse
import com.burdantap.util.generateNineDigitNumber
import com.burdantap.util.normalizeProductTitle
import com.burdantap.util.toSlug

fun ProductDto.toEntity() = ProductEntity(
    modelCode = modelCode,
    storeId = storeId,
    detailsId = listOf(),
    descriptions = description
)

fun ProductDetailDto.toEntity(modelCode: String) = ProductDetailEntity(
    title = title.normalizeProductTitle(),
    slug = "${title.normalizeProductTitle().toSlug()}-${generateNineDigitNumber()}",
    modelCode = modelCode,
)

fun List<ProductDetailDto>.toEntitiesMap(modelCode: String): List<ProductDetailEntity> {
    return this.map { it.toEntity(modelCode = modelCode) }
}

fun List<ProductDetailEntity>.toResponses(): List<ProductDetailResponse> {
    return this.map { detail ->
        ProductDetailResponse(
            id = detail.uuid,
            title = detail.title,
            slug = detail.slug,
        )
    }
}