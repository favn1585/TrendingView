package com.trending.view.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.trending.view.domain.entity.Owner

@JsonClass(generateAdapter = true)
data class OwnerDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String?,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
)

fun OwnerDto.toDomain(): Owner = Owner(
    id = id,
    name = name,
    htmlUrl = htmlUrl,
    avatarUrl = avatarUrl
)