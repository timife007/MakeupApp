package com.timife.makeup.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timife.makeup.data.local.model.MakeupItemEntity

@Dao
interface MakeupItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMakeupItems(
        makeupBrandEntities: List<MakeupItemEntity>
    )

    @Query("DELETE FROM makeupItems_entity")
    suspend fun clearAllMakeupItems()

    @Query("SELECT * FROM makeupItems_entity WHERE :brand == brand")
    suspend fun getAllMakeupItems(brand:String): List<MakeupItemEntity>

    @Query("SELECT * FROM makeupItems_entity")
    suspend fun getMakeupItems(): List<MakeupItemEntity>

}
