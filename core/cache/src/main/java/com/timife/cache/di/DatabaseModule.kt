package com.timife.cache.di

import android.app.Application
import androidx.room.Room
import com.timife.cache.database.MakeupDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMakeupDatabase(app: Application): MakeupDatabase {
        return Room.databaseBuilder(
            app,
            MakeupDatabase::class.java,
            "makeups.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMakeupDao(
        makeupDatabase: MakeupDatabase
    ): com.timife.cache.database.BrandDao {
        return makeupDatabase.dao
    }

    @Provides
    @Singleton
    fun provideMakeupItemDao(
        makeupDatabase: MakeupDatabase
    ): com.timife.cache.database.MakeupItemDao {
        return makeupDatabase.makeupItemDao
    }
}