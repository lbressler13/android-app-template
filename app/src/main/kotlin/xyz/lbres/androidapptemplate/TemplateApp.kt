package xyz.lbres.androidapptemplate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.lbres.androidapptemplate.ui.attributions.AttributionsScreen
import xyz.lbres.androidapptemplate.ui.home.HomeScreen


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TemplateApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.title_action_bar))
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            AppNavHost(navController)
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = NavDestinations.Home.route) {
        composable(route = NavDestinations.Home.route) { HomeScreen(navController) }
        composable(route = NavDestinations.Attributions.route) { AttributionsScreen() }
//        NavDestinations.entries.forEach { dest ->
//            composable(route = dest.route)  { dest.screen() }
//        }
//        composable(route = Routes.home) { HomeScreen() }
//        composable(route = Routes.attributions) { AttributionsScreen() }
    }
}

@Composable
fun ActionBar() {
}
