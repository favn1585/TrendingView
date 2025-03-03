package com.trending.view.feature.trending.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.trending.view.R
import com.trending.view.domain.entity.Repository
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreview
import com.trending.view.util.preview.repository

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrendingItem(
    index: Long,
    repository: Repository
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier.size(32.dp),
                model = repository.owner.avatarUrl,
                contentDescription = null
            )

            TrendingItemTitle(
                modifier = Modifier.align(Alignment.CenterVertically),
                index = index,
                title = repository.name
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = repository.description
        )

        Spacer(Modifier.height(16.dp))

        TrendingItemTopics(repository.topics)

        Spacer(Modifier.height(8.dp))

        HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)

        Spacer(Modifier.height(16.dp))

        FlowRow {
            repository.forks?.let {
                TrendingInfoItem(
                    icon = R.drawable.ic_fork,
                    text = stringResource(R.string.forks, it)
                )
            }

            repository.issues?.let {
                TrendingInfoItem(
                    icon = R.drawable.ic_issue,
                    text = stringResource(R.string.issues, it)
                )
            }

            repository.watchers?.let {
                TrendingInfoItem(
                    icon = R.drawable.ic_watcher,
                    text = stringResource(R.string.watchers, it)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
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
                text = stringResource(R.string.stars, repository.stars)
            )
        }
    }
}

@DarkLightPreview
@Composable
fun TrendingItemPreview() {
    TrendingViewTheme {
        TrendingItem(
            index = 1L,
            repository = repository
        )
    }
}