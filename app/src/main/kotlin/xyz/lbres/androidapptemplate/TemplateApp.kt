package xyz.lbres.androidapptemplate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.lbres.androidapptemplate.ui.attributions.AttributionsScreen
import xyz.lbres.androidapptemplate.ui.home.HomeScreen


@Composable
fun TemplateApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { ActionBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle click */ }) {
                Text(
                    text = stringResource(R.string.dev_tools_button)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Start,
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
    NavHost(navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) { HomeScreen(navController) }
        composable(route = Routes.Attributions.route) { AttributionsScreen() }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ActionBar() {
    TopAppBar(
        title = {
            Text(stringResource(R.string.title_action_bar))
        }
    )
}
