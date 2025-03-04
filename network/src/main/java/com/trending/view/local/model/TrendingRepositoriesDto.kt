package com.trending.view.local.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingRepositoriesDto(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "items")
    val items: List<RepositoryDto>
)