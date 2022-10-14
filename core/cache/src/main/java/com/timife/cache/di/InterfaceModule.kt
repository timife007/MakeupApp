package com.timife.cache.di

import com.timife.cache.Cache
import com.timife.cache.CacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Binds
    @Singleton
    abstract fun bindCache(cacheImpl: CacheImpl): Cache
}