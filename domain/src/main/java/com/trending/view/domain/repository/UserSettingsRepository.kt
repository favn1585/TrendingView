package com.trending.view.domain.repository

interface UserSettingsRepository {
    suspend fun getIsDarkThemeEnabled(): Boolean
}