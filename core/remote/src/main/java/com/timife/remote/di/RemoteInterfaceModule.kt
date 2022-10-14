package com.timife.remote.di

import com.timife.remote.Remote
import com.timife.remote.RemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteInterfaceModule {
    @Binds
    @Singleton
    abstract fun bindRemote(remoteImpl: RemoteImpl): Remote
}