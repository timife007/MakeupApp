package com.timife.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MakeupItem(
    val id:Int,
    val name:String,
    val description:String,
    val price:String,
    val productType:String,
    val priceSign:String,
    val rating:Double,
    val brand:String,
    val currency:String,
    val imageLink:String
):Parcelable
