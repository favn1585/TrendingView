package com.trending.view.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val id: Long,
    val name: String,
    val owner: Owner,
    val description: String,
    val topics: List<String>,
    val size: Int,
    val forks: Long?,
    val issues: Long?,
    val watchers: Long?,
    val htmlUrl: String,
)