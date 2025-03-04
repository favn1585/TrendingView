package com.trending.view.feature.trendinginfo.model

sealed class TrendingInfoScreenUiAction {
    data object OnBackClick : TrendingInfoScreenUiAction()
}
