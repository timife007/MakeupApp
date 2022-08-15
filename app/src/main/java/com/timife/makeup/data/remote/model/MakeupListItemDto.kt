package com.timife.makeup.data.remote.model


import com.squareup.moshi.Json

data class MakeupListItemDto(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "brand")
    val brand: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "price")
    val price: String?,
    @field:Json(name = "price_sign")
    val priceSign: Any?,
    @field:Json(name = "currency")
    val currency: Any?,
    @field:Json(name = "image_link")
    val imageLink: String?,
    @field:Json(name = "product_link")
    val productLink: String?,
    @field:Json(name = "website_link")
    val websiteLink: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "rating")
    val rating: Double?,
    @field:Json(name = "category")
    val category: String?,
    @field:Json(name = "product_type")
    val productType: String?,
    @field:Json(name = "tag_list")
    val tagList: List<Any?>?,
    @field:Json(name = "created_at")
    val createdAt: String?,
    @field:Json(name = "updated_at")
    val updatedAt: String?,
    @field:Json(name = "product_api_url")
    val productApiUrl: String?,
    @field:Json(name = "api_featured_image")
    val apiFeaturedImage: String?,
    @field:Json(name = "product_colors")
    val productColors: List<ProductColor?>?
)