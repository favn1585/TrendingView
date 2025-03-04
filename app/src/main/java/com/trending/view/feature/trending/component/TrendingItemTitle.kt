package com.trending.view.feature.trending.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.trending.view.R
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreviewNoBackground
import com.trending.view.util.preview.repository

@Composable
fun TrendingItemTitle(
    modifier: Modifier = Modifier,
    index: Int,
    title: String
) {
    Row(
        modifier = modifier
    ) {
        val annotatedString = buildAnnotatedString {

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            ) {
                append(stringResource(R.string.repository_title_prefix))
            }

            append(title)
        }

        Text(
            modifier = Modifier.weight(1f),
            text = annotatedString,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = "#${index + 1}",
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@DarkLightPreviewNoBackground
@Composable
private fun TrendingItemTitlePreview() {
    TrendingViewTheme {
        TrendingItemTitle(
            index = 0,
            title = repository.name
        )
    }
}