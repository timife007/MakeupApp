package com.timife.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timife.cache.model.MakeupBrandEntity
import com.timife.cache.model.MakeupItemEntity

@Database(
    entities = [MakeupBrandEntity::class, MakeupItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MakeupDatabase : RoomDatabase() {
    abstract val dao: BrandDao
    abstract val makeupItemDao: MakeupItemDao
}