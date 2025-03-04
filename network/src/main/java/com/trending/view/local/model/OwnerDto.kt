package com.trending.view.local.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.trending.view.domain.entity.Owner

@JsonClass(generateAdapter = true)
data class OwnerDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "login")
    val login: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
)

fun OwnerDto.toDomain(): Owner = Owner(
    id = id,
    name = login,
    htmlUrl = htmlUrl,
    avatarUrl = avatarUrl
)