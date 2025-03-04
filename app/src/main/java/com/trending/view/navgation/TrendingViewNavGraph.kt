package com.trending.view.navgation

import androidx.navigation.NavGraphBuilder
import com.trending.view.domain.entity.Repository
import com.trending.view.feature.trending.TrendingScreen
import com.trending.view.feature.trendinginfo.TrendingInfoScreen

object TrendingViewNavGraph : (NavGraphBuilder) -> Unit {

    override fun invoke(builder: NavGraphBuilder) {

        NavigationCommand.Trending.configure(builder) {
            TrendingScreen()
        }

        NavigationCommand.TrendingInfo.configure(builder, Repository.serializer()) {
            TrendingInfoScreen(it)
        }
    }
}