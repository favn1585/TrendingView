package com.trending.view.navgation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
class NavigationModule {

    @Provides
    @ActivityRetainedScoped
    fun navigator(): Navigator = Navigator()
}
