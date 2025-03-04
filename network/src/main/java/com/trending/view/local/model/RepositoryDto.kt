package com.trending.view.local.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.trending.view.domain.entity.Repository

@JsonClass(generateAdapter = true)
data class RepositoryDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "owner")
    val owner: OwnerDto,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "forks_count")
    val forksCount: Long,
    @Json(name = "open_issues_count")
    val openIssuesCount: Long,
    @Json(name = "topics")
    val topics: List<String>?,
    @Json(name = "size")
    val size: Int,
    @Json(name = "watchers")
    val watchers: Long,
)

fun RepositoryDto.toDomain() = Repository(
    id = id,
    name = name,
    owner = owner.toDomain(),
    htmlUrl = htmlUrl,
    description = description.orEmpty(),
    forks = forksCount,
    issues = openIssuesCount,
    topics = topics?.take(4) ?: emptyList(),
    size = size,
    watchers = watchers
)