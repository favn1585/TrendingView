package com.trending.view.feature.trending.usecase

import com.trending.view.domain.repository.TrendingRepository
import javax.inject.Inject

class GetTrendingRepositoriesUseCase @Inject constructor(private val repository: TrendingRepository) {
    suspend fun execute(count: Int, page: Int) =
        repository.getTrendingRepositories(count = count, page = page)
}