package com.trending.view.app.di

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Reusable
    fun context(@ApplicationContext context: Context): Context = context
}