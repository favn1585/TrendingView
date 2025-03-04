package com.trending.view.main

import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.lifecycle.ViewModel
import com.trending.view.main.model.MainUiAction
import com.trending.view.main.usecase.GetIsDarkModeEnabledUseCase
import com.trending.view.main.usecase.SetDarkModeEnabledUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getIsDarkModeEnabledUseCase: GetIsDarkModeEnabledUseCase,
    private val setDarkModeEnabledUseCase: SetDarkModeEnabledUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState(isDarkModeEnabled = false))
    val viewState = _viewState.asStateFlow()

    init {
        getIsDarkModeEnabledUseCase().run {
            if (this) {
                setDarkMode()
            }
        }
    }

    fun onUiAction(uiAction: MainUiAction) {
        when (uiAction) {
            MainUiAction.OnSetDarkModeEnabled -> {
                setDarkModeEnabledUseCase(params = true)
                setDarkMode()
            }

            MainUiAction.OnSetSystemThemeEnabled -> {
                setDarkModeEnabledUseCase(params = false)
                setSystemThemeMode()
            }
        }
    }

    private fun setDarkMode() {
        _viewState.value = _viewState.value.copy(isDarkModeEnabled = true)
        setDefaultNightMode(MODE_NIGHT_YES)
    }

    private fun setSystemThemeMode() {
        _viewState.value = _viewState.value.copy(isDarkModeEnabled = false)
        setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
    }
}
