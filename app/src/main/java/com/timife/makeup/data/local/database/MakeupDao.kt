package com.timife.makeup.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timife.makeup.data.local.model.MakeupBrandEntity

@Dao
interface MakeupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMakeupBrands(
        makeupBrandEntities: List<MakeupBrandEntity>
    )

    @Query("DELETE FROM makeupBrands_entity")
    suspend fun clearAllBrands()

    @Query("SELECT * FROM makeupBrands_entity")
    suspend fun getAllBrands(): List<MakeupBrandEntity>
}
