package com.trending.view.domain.repository

import com.trending.view.domain.entity.Repository

interface TrendingRepository {
    suspend fun getTrendingRepositories(
        count: Int, page: Int
    ): Result<List<Repository>>
}