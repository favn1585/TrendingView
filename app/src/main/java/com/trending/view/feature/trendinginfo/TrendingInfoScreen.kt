package com.trending.view.feature.trendinginfo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.trending.view.R
import com.trending.view.domain.entity.Repository
import com.trending.view.feature.trending.component.TrendingInfoItem
import com.trending.view.feature.trending.component.TrendingItemTopics
import com.trending.view.feature.trendinginfo.model.TrendingInfoScreenUiAction
import com.trending.view.ui.components.Loader
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreview
import com.trending.view.util.preview.repository
import com.trending.view.util.preview.toReadableFormat

@Composable
fun TrendingInfoScreen(repository: Repository) {
    val viewModel: TrendingInfoViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsState().value

    LaunchedEffect(repository) {
        viewModel.init(repository)
    }

    if (viewState.repository != null) {
        TrendingInfoScreenContent(
            onUiAction = viewModel::onUiAction,
            repository = viewState.repository
        )
    }

    Loader(viewState.repository == null, modifier = Modifier.fillMaxSize())
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TrendingInfoScreenContent(
    onUiAction: (TrendingInfoScreenUiAction) -> Unit = {},
    repository: Repository
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.repository_info),
                    fontWeight = FontWeight.Medium
                )
            }, colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            ),
            navigationIcon = {
                IconButton(onClick = {
                    onUiAction(TrendingInfoScreenUiAction.OnBackClick)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            val composition = rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.loading)
            ).value

            val progress = animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                speed = 2f
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress.value },
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                )

                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                        .align(Alignment.Center),
                    model = repository.owner.avatarUrl,
                    contentDescription = null
                )

                repository.owner.name?.let { name ->
                    Column(
                        modifier = Modifier.align(Alignment.BottomCenter)
                            .offset(y = (-16).dp)
                            .shadow(elevation = 4.dp, shape = CircleShape)
                            .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
                            .padding(vertical = 8.dp, horizontal = 16.dp)

                    ) {
                        Text(text = name, color = MaterialTheme.colorScheme.onBackground)
                    }
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)

            Spacer(Modifier.height(16.dp))

            FlowRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repository.forks?.let {
                    TrendingInfoItem(
                        icon = R.drawable.ic_fork,
                        text = stringResource(R.string.forks, it.toReadableFormat())
                    )
                }

                repository.issues?.let {
                    TrendingInfoItem(
                        icon = R.drawable.ic_issue,
                        text = stringResource(R.string.issues, it.toReadableFormat())
                    )
                }

                repository.watchers?.let {
                    TrendingInfoItem(
                        icon = R.drawable.ic_watcher,
                        text = stringResource(R.string.watchers, it.toReadableFormat())
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Spacer(Modifier.width(16.dp))
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = repository.description
                )

                if (repository.topics.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    TrendingItemTopics(
                        modifier = Modifier.fillMaxWidth(),
                        topics = repository.topics,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(16.dp))

            HorizontalDivider(
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                thickness = 1.dp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                    text = stringResource(R.string.size, repository.size)
                )
            }
        }
    }
}

@DarkLightPreview
@Composable
private fun TrendingScreenContentPreview() {
    TrendingViewTheme {
        TrendingInfoScreenContent(
            repository = repository,
        )
    }
}

