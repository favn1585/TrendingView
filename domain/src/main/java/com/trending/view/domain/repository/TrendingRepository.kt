package com.trending.view.domain.repository

import com.trending.view.domain.entity.Repository
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun getTrendingRepositories(): Flow<List<Repository>>
}