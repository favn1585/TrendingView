package com.trending.view.local.repository

import android.content.SharedPreferences
import com.trending.view.domain.repository.UserSettingsRepository
import javax.inject.Inject

private const val IS_DARK_MODE_ENABLED_KEY = "is_dark_mode_enabled"

class LocalStorageRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserSettingsRepository {
    override fun getIsDarkThemeEnabled(): Boolean {
        return sharedPreferences.getBoolean(IS_DARK_MODE_ENABLED_KEY, false)
    }

    override fun setIsDarkThemeEnabled(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean(IS_DARK_MODE_ENABLED_KEY, isEnabled).apply()
    }
}