package com.trending.view.network.repository

import com.trending.view.domain.entity.Repository
import com.trending.view.network.api.GitHubApi
import com.trending.view.network.model.toDomain
import javax.inject.Inject

class RemoteGitHubRepository @Inject constructor(
    private val githubApi: GitHubApi
) {
    suspend fun getRepositories(
        count: Int, page: Int
    ): Result<List<Repository>> = try {
        githubApi.getTrendingRepositories(
            count = count, page = page
        ).run {
            if (this.isSuccessful) {
                val items = body()?.items?.map { it.toDomain() }.orEmpty()
                return Result.success(items)
            } else {
                return Result.failure(Exception())
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}