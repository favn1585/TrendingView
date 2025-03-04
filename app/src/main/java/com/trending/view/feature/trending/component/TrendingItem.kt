package com.trending.view.feature.trending.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.trending.view.R
import com.trending.view.domain.entity.Repository
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreview
import com.trending.view.util.preview.repository
import com.trending.view.util.preview.toReadableFormat

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrendingItem(
    index: Int,
    repository: Repository,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        ),
                    model = repository.owner.avatarUrl,
                    contentDescription = null
                )

                Spacer(Modifier.width(16.dp))

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

            if (repository.topics.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                TrendingItemTopics(modifier = Modifier.fillMaxWidth(), topics = repository.topics)
            }

            Spacer(Modifier.height(8.dp))
        }

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

        HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
    }
}

@DarkLightPreview
@Composable
fun TrendingItemPreview() {
    TrendingViewTheme {
        TrendingItem(
            index = 1,
            repository = repository
        )
    }
}