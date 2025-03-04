package com.trending.view.feature.trendinginfo

import androidx.lifecycle.ViewModel
import com.trending.view.domain.entity.Repository
import com.trending.view.feature.trendinginfo.model.TrendingInfoScreenUiAction
import com.trending.view.navgation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TrendingInfoViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    private val _viewState = MutableStateFlow(TrendingInfoViewState())
    val viewState = _viewState.asStateFlow()

    fun init(repository: Repository) {
        _viewState.update {
            it.copy(repository = repository)
        }
    }

    fun onUiAction(action: TrendingInfoScreenUiAction) {
        when (action) {
            TrendingInfoScreenUiAction.OnBackClick -> {
                navigator.goBack()
            }
        }
    }
}
