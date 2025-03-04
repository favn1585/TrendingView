package com.trending.view.repository.di

import com.trending.view.domain.repository.TrendingRepository
import com.trending.view.domain.repository.UserSettingsRepository
import com.trending.view.repository.repository.TrendingRepositoryImpl
import com.trending.view.repository.repository.UserSettingsRepositoryImpl
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
    abstract fun provideTrendingRepository(repository: TrendingRepositoryImpl): TrendingRepository

    @Binds
    @Singleton
    abstract fun provideUserSettingsRepository(repository: UserSettingsRepositoryImpl): UserSettingsRepository
}