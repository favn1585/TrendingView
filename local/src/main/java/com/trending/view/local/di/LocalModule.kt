package com.trending.view.local.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val SHARED_PREFS_NAME = "trending_view_shared_prefs"

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Reusable
    fun provideSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }
}