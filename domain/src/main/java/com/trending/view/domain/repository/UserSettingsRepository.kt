package com.trending.view.domain.repository

interface UserSettingsRepository {
    fun getIsDarkThemeEnabled(): Boolean
    fun setIsDarkThemeEnabled(isEnabled: Boolean)
}