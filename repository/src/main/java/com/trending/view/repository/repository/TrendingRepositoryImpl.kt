package com.trending.view.repository.repository

import com.trending.view.domain.repository.TrendingRepository
import com.trending.view.network.repository.RemoteGitHubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val remoteGitHubRepository: RemoteGitHubRepository,
) : TrendingRepository {
    override suspend fun getTrendingRepositories(
        count: Int, page: Int
    ) = withContext(Dispatchers.IO) {
        return@withContext remoteGitHubRepository.getRepositories(count = count, page = page)
    }
}