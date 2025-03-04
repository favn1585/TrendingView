package com.trending.view.local.api

import com.trending.view.local.model.TrendingRepositoriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories?q=stars:>100000&sort=stars")
    suspend fun getTrendingRepositories(
        @Query("per_page") count: Int,
        @Query("page") page: Int
    ): Response<TrendingRepositoriesDto>
}
