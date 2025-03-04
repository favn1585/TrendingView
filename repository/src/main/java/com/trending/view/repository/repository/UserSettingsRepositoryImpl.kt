package com.trending.view.repository.repository

import com.trending.view.domain.repository.UserSettingsRepository
import com.trending.view.local.repository.LocalStorageRepository
import javax.inject.Inject

class UserSettingsRepositoryImpl @Inject constructor(
    private val localStorageRepository: LocalStorageRepository,
) : UserSettingsRepository by localStorageRepository