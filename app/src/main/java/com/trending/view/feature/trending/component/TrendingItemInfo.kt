package com.trending.view.feature.trending.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.trending.view.R
import com.trending.view.ui.theme.TrendingViewTheme
import com.trending.view.util.preview.DarkLightPreviewNoBackground

@Composable
fun TrendingInfoItem(
    @DrawableRes icon: Int,
    text: String
) {
    Box(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
                .padding(vertical = 4.dp)
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterVertically),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = null,
                painter = painterResource(icon)
            )

            Spacer(Modifier.width(4.dp))

            Text(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
                text = text
            )
        }
    }
}

@DarkLightPreviewNoBackground
@Composable
fun TrendingInfoItemPreview() {
    TrendingViewTheme {
        TrendingInfoItem(
            icon = R.drawable.ic_fork,
            text = "Forks: 12346"
        )
    }
}