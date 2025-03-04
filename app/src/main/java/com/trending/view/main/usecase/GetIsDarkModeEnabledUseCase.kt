package com.trending.view.main.usecase

import com.trending.view.domain.repository.UserSettingsRepository
import javax.inject.Inject

class GetIsDarkModeEnabledUseCase @Inject constructor(private val repository: UserSettingsRepository) {
    operator fun invoke() = repository.getIsDarkThemeEnabled()
}