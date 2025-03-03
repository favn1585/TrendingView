package com.trending.view.domain.entity

data class Repository(
    val id: Long,
    val name: String,
    val owner: Owner,
    val description: String,
    val topics: List<String>,
    val stars: Int,
    val forks: Int?,
    val issues: Int?,
    val watchers: Int?
)