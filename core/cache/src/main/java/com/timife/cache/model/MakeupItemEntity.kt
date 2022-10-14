package com.timife.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "makeupItems_entity")
data class MakeupItemEntity(
    @PrimaryKey val id:Int? = null,
    val name:String,
    val imageUrl:String,
    val productType:String,
    val priceSign:String,
    val brand:String,
    val description:String,
    val price:String,
    val currency: String,
    val rating:Double
)
