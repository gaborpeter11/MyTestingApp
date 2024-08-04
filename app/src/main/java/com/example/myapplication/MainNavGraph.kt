package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.activation.ActivationScreen
import com.example.myapplication.main_screen.HomeScreen
import com.example.myapplication.scratch_screen.DetailScreen
import com.example.myapplication.ui.common.CardState
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    data object Home : Screen("home/{cardState}")
    data object Detail : Screen("detail/{cardState}")
    data object Activation : Screen("activation")
}

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            Screen.Home.route,
            arguments = listOf(navArgument("cardState") { type = NavType.StringType })
        ) { backStackEntry ->
            val cardStateString =
                backStackEntry.arguments?.getString("cardState")
                    ?: CardState.NOT_SCRATCHED_CARD.name
            val cardState = CardState.valueOf(cardStateString)
            HomeScreen(
                onNavigateToDetail = {
                    navController.navigate(
                        routeWithParams(
                            "detail",
                            "cardType" to it.name
                        )
                    )
                },
                onNavigateToActivation = { navController.navigate("activation") },
                cardState = cardState
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("cardState") { type = NavType.StringType })
        )
        { backStackEntry ->
            val cardStateString =
                backStackEntry.arguments?.getString("cardState")
                    ?: CardState.NOT_SCRATCHED_CARD.name
            val cardState = CardState.valueOf(cardStateString)

            DetailScreen(
                goBack = {
                    navController.navigate(
                        routeWithParams(
                            "home",
                            "cardType" to it.name
                        )
                    )
                },
                cardState = cardState
            )
        }
        composable(route = Screen.Activation.route)
        {
            ActivationScreen(goBack = { navController.popBackStack() })
        }
    }
}


fun routeWithParams(baseRoute: String, vararg params: Pair<String, String>): String {
    return buildString {
        append(baseRoute)
        params.forEach { (key, value) ->
            append("/${URLEncoder.encode(value, StandardCharsets.UTF_8.toString())}")
        }
    }
}
