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
            getEventsUseCase.execute(
                page = size / PAGE_SIZE + 1,
                count = PAGE_SIZE
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

        return if (data.isSuccess) {
            LoadResult.Page(
                data = data.getOrNull() ?: emptyList(),
                prevKey = size - PAGE_SIZE,
                nextKey = size + PAGE_SIZE,
            )
        } else {
            LoadResult.Error(IllegalStateException())
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}