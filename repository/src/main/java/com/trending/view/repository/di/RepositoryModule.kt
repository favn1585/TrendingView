package com.trending.view.repository.di

import com.trending.view.domain.repository.TrendingRepository
import com.trending.view.repository.repository.TrendingRepositoryImpl
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
    abstract fun provideLoyaltyRepository(repository: TrendingRepositoryImpl): TrendingRepository
}