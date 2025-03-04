package com.trending.view.main.model

sealed class MainUiAction {
    data object OnSetDarkModeEnabled : MainUiAction()
    data object OnSetSystemThemeEnabled : MainUiAction()
}
