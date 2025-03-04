package com.trending.view.feature.trending.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.trending.view.domain.entity.Repository
import com.trending.view.feature.trending.usecase.GetTrendingRepositoriesUseCase
import javax.inject.Inject

class TrendingRepositoriesPagingSource @Inject constructor(
    private val getEventsUseCase: GetTrendingRepositoriesUseCase,
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val size = params.key ?: 0

        val data = try {
            getEventsUseCase(
                page = size / PAGE_SIZE + 1,
                count = PAGE_SIZE
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

        return if (data.isSuccess) {
            val repositories = data.getOrNull() ?: emptyList()
            val prevKey = if (size > 0) size - PAGE_SIZE else null
            val nextKey = if (repositories.isNotEmpty()) size + PAGE_SIZE else null

            LoadResult.Page(
                data = repositories,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } else {
            LoadResult.Error(IllegalStateException())
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}