package com.trending.view.feature.trending.model

import com.trending.view.domain.entity.Repository

sealed class TrendingScreenUiAction {
    data class OnOpenDetails(val repository: Repository) : TrendingScreenUiAction()
}
