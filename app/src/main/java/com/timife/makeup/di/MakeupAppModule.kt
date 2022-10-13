package com.timife.makeup.di

import android.app.Application
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.timife.makeup.data.local.database.BrandDao
import com.timife.makeup.data.local.database.MakeupDatabase
import com.timife.makeup.data.local.database.MakeupItemDao
import com.timife.makeup.data.remote.MakeupApi
import com.timife.makeup.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MakeupAppModule {
    @Provides
    @Singleton
    fun provideMakeupApi(app: Application): MakeupApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_LIST)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        ChuckerInterceptor.Builder(app)
                            .collector(ChuckerCollector(app, showNotification = true, retentionPeriod = RetentionManager.Period.ONE_WEEK))
                            .maxContentLength(250000L)
                            .redactHeaders(emptySet())
                            .alwaysReadResponseBody(false)
                            .build()
                    )
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create(MakeupApi::class.java)
    }

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
    ): BrandDao {
        return makeupDatabase.dao
    }

    @Provides
    @Singleton
    fun provideMakeupItemDao(
        makeupDatabase: MakeupDatabase
    ):MakeupItemDao{
        return makeupDatabase.makeupItemDao
    }
}