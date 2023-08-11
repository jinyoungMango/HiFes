package com.ssafy.hifes.di.module

import com.ssafy.hifes.data.repository.user.UserRepository
import com.ssafy.hifes.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsUserRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository
}