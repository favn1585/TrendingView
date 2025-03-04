package com.trending.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.trending.view.R
import com.trending.view.main.model.MainUiAction
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
            val viewModel: MainViewModel = hiltViewModel()
            val viewState = viewModel.viewState.collectAsState().value
            val navController = rememberNavController()
            navigator.bindNavController(navController)

            val isDarkTheme = if (viewState.isDarkModeEnabled) true else isSystemInDarkTheme()

            TrendingViewTheme(darkTheme = isDarkTheme) {
                ProvideWindowInsets {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationCommand.Trending.path(),
                        builder = TrendingViewNavGraph,
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    )
                }
            }

            SwitchTheme(isDarkTheme = isDarkTheme, onUiAction = viewModel::onUiAction)
        }
    }
}

@Composable
fun SwitchTheme(
    isDarkTheme: Boolean,
    onUiAction: (MainUiAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(16.dp)
    ) {
        FloatingActionButton(
            modifier = Modifier.align(alignment = Alignment.BottomEnd),
            onClick = {
                if(isDarkTheme) {
                    onUiAction(MainUiAction.OnSetSystemThemeEnabled)
                } else {
                    onUiAction(MainUiAction.OnSetDarkModeEnabled)
                }
            },
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_theme),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = stringResource(
                        if (isDarkTheme) {
                            R.string.set_system_theme
                        } else {
                            R.string.set_dark_theme
                        }
                    ),
                )
            }
        }
    }
}