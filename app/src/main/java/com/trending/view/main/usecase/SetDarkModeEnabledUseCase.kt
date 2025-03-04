package com.trending.view.main.usecase

import com.trending.view.domain.repository.UserSettingsRepository
import javax.inject.Inject

class SetDarkModeEnabledUseCase @Inject constructor(private val repository: UserSettingsRepository) {
    operator fun invoke(params: Boolean) = repository.setIsDarkThemeEnabled(params)
}