package com.timife.makeup.di

import com.timife.makeup.data.local.Cache
import com.timife.makeup.data.local.CacheImpl
import com.timife.makeup.data.remote.Remote
import com.timife.makeup.data.remote.RemoteImpl
import com.timife.makeup.data.repositories.BrandRepositoryImpl
import com.timife.makeup.data.repositories.MakeupItemsRepositoryImpl
import com.timife.makeup.domain.repositories.BrandRepository
import com.timife.makeup.domain.repositories.MakeupItemsRepository
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
    abstract fun bindMakeupBrandRepository(makeupBrandRepositoryImpl: BrandRepositoryImpl): BrandRepository

    @Binds
    @Singleton
    abstract fun bindMakeupItemsRepository(makeupItemsRepositoryImpl: MakeupItemsRepositoryImpl): MakeupItemsRepository

    @Binds
    @Singleton
    abstract fun bindCache(cacheImpl: CacheImpl):Cache

    @Binds
    @Singleton
    abstract fun bindRemote(remoteImpl: RemoteImpl):Remote
}