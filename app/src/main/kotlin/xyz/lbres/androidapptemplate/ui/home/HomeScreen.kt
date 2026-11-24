package xyz.lbres.androidapptemplate.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import xyz.lbres.androidapptemplate.Routes
import xyz.lbres.androidapptemplate.R


@Composable
fun HomeScreen(navController: NavController) {
    // val navController = rememberNavController()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            // TODO is theme needed?
            MaterialTheme {
                GreetingMessage()
                InfoButton(navController)
            }
        }
    }
}

@Composable
fun GreetingMessage() {
    val modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp)
        .padding(top = 80.dp)
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.welcome_message),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                // TODO add back after theme is configured
                // color = MaterialTheme.colorScheme.onBackground,
            ),
        )
    }
}

@Composable
fun InfoButton(navController: NavController) {
    val padding = dimensionResource(R.dimen.corner_icon_padding)
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = { navController.navigate(Routes.Attributions.route) },
            modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = padding, end = padding),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = stringResource(R.string.info_cd),
                // tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
