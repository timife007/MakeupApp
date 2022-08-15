package com.timife.makeup.data.remote.model


import com.squareup.moshi.Json

data class ProductColor(
    @field:Json(name = "hex_value")
    val hexValue: String?,
    @field:Json(name = "colour_name")
    val colourName: String?
)