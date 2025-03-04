package com.trending.view.util.preview

import com.trending.view.domain.entity.Owner
import com.trending.view.domain.entity.Repository

val repository = Repository(
    id = 1L,
    name = "Test",
    owner = Owner(
        id = 1L,
        name = "Test",
        avatarUrl = "",
        htmlUrl = ""
    ),
    description = "Description of the repository",
    topics = listOf(
        "test",
        "long topic",
        "some123",
        "1",
        "#test",
        "teewrst",
        "long topic",
        "some123",
        "1",
        "#test",
        "test",
        "londsfsfg topic",
        "some123",
        "1",
        "#test",
    ),
    size = 15,
    issues = 10,
    watchers = 23,
    forks = 12,
    htmlUrl = "www.some.thing"
)