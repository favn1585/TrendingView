package com.trending.view.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    val id: Long,
    val name: String?,
    val avatarUrl: String,
    val htmlUrl: String,
)