package com.timife.makeup.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timife.makeup.data.local.model.MakeupBrandEntity
import com.timife.makeup.data.local.model.MakeupItemEntity

@Database(
    entities = [MakeupBrandEntity::class,MakeupItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MakeupDatabase : RoomDatabase() {
    abstract val dao: BrandDao
    abstract val makeupItemDao:MakeupItemDao
}