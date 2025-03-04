package com.trending.view.feature.trending.usecase

import com.trending.view.domain.entity.Repository
import com.trending.view.domain.repository.TrendingRepository
import timber.log.Timber
import javax.inject.Inject

class GetTrendingRepositoriesUseCase @Inject constructor(private val repository: TrendingRepository) {
    suspend operator fun invoke(count: Int, page: Int): Result<List<Repository>> {
        Timber.d("Loading items: count: $count, page: $page")
        val result = repository.getTrendingRepositories(count = count, page = page)
        Timber.d("Loading items result: $result")
        return result
    }
}