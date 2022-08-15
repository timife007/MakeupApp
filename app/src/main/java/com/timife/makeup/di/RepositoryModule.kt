package com.timife.makeup.di

import com.timife.makeup.data.repositories.MakeupBrandRepositoryImpl
import com.timife.makeup.data.repositories.MakeupItemsRepositoryImpl
import com.timife.makeup.domain.repositories.MakeupBrandRepository
import com.timife.makeup.domain.repositories.MakeupItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMakeupBrandRepository(makeupBrandRepositoryImpl: MakeupBrandRepositoryImpl): MakeupBrandRepository

    @Binds
    @Singleton
    abstract fun bindMakeupItemsRepository(makeupItemsRepositoryImpl: MakeupItemsRepositoryImpl): MakeupItemsRepository
}