package xyz.lbres.androidapptemplate

import androidx.compose.runtime.Composable
import xyz.lbres.androidapptemplate.ui.attributions.AttributionsScreen
import xyz.lbres.androidapptemplate.ui.home.HomeScreen

enum class NavDestinations(val route: String) {
    Home("home"),
    Attributions("attributions"),
}
