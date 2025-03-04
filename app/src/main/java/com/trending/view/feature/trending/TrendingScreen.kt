package com.trending.view.feature.trending

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.trending.view.R
import com.trending.view.domain.entity.Repository
import com.trending.view.feature.trending.component.TrendingItem
import com.trending.view.feature.trending.model.TrendingScreenUiAction
import com.trending.view.ui.components.Loader
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreview
import com.trending.view.util.preview.repository
import kotlinx.coroutines.flow.flowOf

@Composable
fun TrendingScreen() {
    val viewModel: TrendingViewModel = hiltViewModel()
    val repositories = viewModel.repositoriesData.collectAsLazyPagingItems()

    TrendingScreenContent(
        repositories = repositories,
        onUiAction = viewModel::onUiAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingScreenContent(
    repositories: LazyPagingItems<Repository>,
    onUiAction: (TrendingScreenUiAction) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(R.string.trending_repositories),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium
            )
        })
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(repositories.itemCount, key = {
                repositories.peek(it)?.id.toString()
            }) { index ->
                repositories[index]?.let { repository ->
                    TrendingItem(index = index, repository = repository) {
                        onUiAction(TrendingScreenUiAction.OnOpenDetails(repository))
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
    Loader(
        modifier = Modifier.fillMaxSize(),
        isVisible = repositories.loadState.refresh == LoadState.Loading
    )
}

@DarkLightPreview
@Composable
fun TrendingScreenContentPreview() {
    TrendingViewTheme {
        TrendingScreenContent(
            repositories = flowOf( PagingData.from(
                listOf(
                    repository,
                    repository
                )
            )).collectAsLazyPagingItems(),
            onUiAction = {}
        )
    }
}

