package com.trending.view.feature.trending.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trending.view.ui.theme.TopicColorsDark
import com.trending.view.ui.theme.TopicColorsLight
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreview
import com.trending.view.util.preview.repository
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrendingItemTopics(
    modifier: Modifier = Modifier,
    topics: List<String>
) {
    FlowRow(
        modifier = modifier
    ) {
        topics.forEach {
            Box(
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            ) {
                TopicItem(it)
            }
        }
    }
}

@Composable
private fun TopicItem(
    text: String
) {
    val color = if (isSystemInDarkTheme()) {
        TopicColorsDark[Random.nextInt(TopicColorsDark.size)]
    } else {
        TopicColorsLight[Random.nextInt(TopicColorsLight.size)]
    }

    Box(
        modifier = Modifier
            .background(color, shape = CircleShape)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodySmall,
            text = text
        )
    }
}

@DarkLightPreview
@Composable
fun TrendingItemTopicsPreview() {
    TrendingViewTheme {
        TrendingItemTopics(
            topics = repository.topics
        )
    }
}