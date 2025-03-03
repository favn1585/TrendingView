package com.trending.view.feature.trending.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreviewNoBackground
import com.trending.view.util.preview.repository

@Composable
fun TrendingItemTitle(
    modifier: Modifier = Modifier,
    index: Long,
    title: String
) {
    val annotatedString = buildAnnotatedString {
        append(title)

        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        ) {
            append(" #$index")
        }
    }

    Text(
        modifier = modifier,
        text = annotatedString,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold,
    )
}

@DarkLightPreviewNoBackground
@Composable
private fun TrendingItemTitlePreview() {
    TrendingViewTheme {
        TrendingItemTitle(
            index = 1L,
            title = repository.name
        )
    }
}