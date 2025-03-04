package com.trending.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.trending.view.navgation.NavigationCommand
import com.trending.view.navgation.Navigator
import com.trending.view.navgation.TrendingViewNavGraph
import com.trending.view.ui.theme.TrendingViewTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            navigator.bindNavController(navController)

            TrendingViewTheme {
                ProvideWindowInsets {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationCommand.Trending.path(),
                        builder = TrendingViewNavGraph,
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    )
                }
            }
        }
    }
}