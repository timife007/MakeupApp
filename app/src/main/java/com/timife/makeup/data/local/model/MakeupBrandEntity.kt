package com.timife.makeup.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "makeupBrands_entity")
data class MakeupBrandEntity(
    @PrimaryKey val id: Int? = null,
    val brand: String
)