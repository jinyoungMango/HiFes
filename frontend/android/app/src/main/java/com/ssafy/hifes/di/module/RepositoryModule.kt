package com.ssafy.hifes.di.module

import com.ssafy.hifes.data.repository.festival.FestivalRepository
import com.ssafy.hifes.data.repository.festival.FestivalRepositoryImpl
import com.ssafy.hifes.data.repository.group.GroupRepository
import com.ssafy.hifes.data.repository.group.GroupRepositoryImpl
import com.ssafy.hifes.data.repository.main.MainRepository
import com.ssafy.hifes.data.repository.main.MainRepositoryImpl
import com.ssafy.hifes.data.repository.proof.ProofRepository
import com.ssafy.hifes.data.repository.proof.ProofRepositoryImpl
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

    @Binds
    abstract fun bindsMainRepository(
        repositoryImpl: MainRepositoryImpl
    ): MainRepository

    @Binds
    abstract fun bindsFestivalRepository(
        repositoryImpl: FestivalRepositoryImpl
    ): FestivalRepository

    @Binds
    abstract fun bindsGroupRepository(
        repositoryImpl: GroupRepositoryImpl
    ): GroupRepository

    @Binds
    abstract fun bindsProofRepository(
        repositoryImpl: ProofRepositoryImpl
    ): ProofRepository
}