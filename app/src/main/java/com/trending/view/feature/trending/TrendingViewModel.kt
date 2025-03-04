package com.trending.view.feature.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trending.view.domain.entity.Repository
import com.trending.view.feature.trending.data.TrendingRepositoriesPagingSource
import com.trending.view.feature.trending.data.TrendingRepositoriesPagingSource.Companion.PAGE_SIZE
import com.trending.view.feature.trending.model.TrendingScreenUiAction
import com.trending.view.navgation.NavigationCommand
import com.trending.view.navgation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val navigator: Navigator,
    private val pagingSource: TrendingRepositoriesPagingSource
) : ViewModel() {
    val repositoriesData: Flow<PagingData<Repository>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE),
        pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)

    fun onUiAction(intent: TrendingScreenUiAction) {
        when (intent) {
            is TrendingScreenUiAction.OnOpenDetails -> navigator.navigate(
                navigationCommand = NavigationCommand.TrendingInfo,
                argument = intent.repository
            )
        }
    }
}
