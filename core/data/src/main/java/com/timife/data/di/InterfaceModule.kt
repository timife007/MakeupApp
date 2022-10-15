package com.timife.data.di

import com.timife.data.repositories.BrandRepositoryImpl
import com.timife.data.repositories.MakeupItemsRepositoryImpl
import com.timife.domain.repositories.BrandRepository
import com.timife.domain.repositories.MakeupItemsRepository
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
}