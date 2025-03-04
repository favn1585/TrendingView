package com.trending.view.local.repository

import com.trending.view.domain.entity.Repository
import com.trending.view.local.api.GitHubApi
import com.trending.view.local.model.toDomain
import timber.log.Timber
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
                Timber.e("Error: ${errorBody()}")
                return Result.failure(Exception())
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}