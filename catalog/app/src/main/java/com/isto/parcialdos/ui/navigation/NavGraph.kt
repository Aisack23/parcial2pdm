package com.isto.parcialdos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isto.parcialdos.ui.view.*
import com.isto.parcialdos.ui.viewmodel.OrderViewModel

object Routes {
    const val HOME = "home"
    const val SEARCH = "search"
    const val ORDERS = "orders"
    const val PRODUCT_DETAIL = "productDetail"
}

@Composable
fun NavGraph(startDestination: String = Routes.HOME) {
    val navController = rememberNavController()
    val orderViewModel: OrderViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }
        composable(Routes.SEARCH) {
            SearchScreen(navController = navController)
        }
        composable(Routes.ORDERS) {
            OrderScreen(navController = navController, orderViewModel = orderViewModel)
        }
        composable(
            "${Routes.PRODUCT_DETAIL}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            productId?.let {
                ProductDetailScreen(
                    productId = it,
                    navController = navController,
                    orderViewModel = orderViewModel
                )
            }
        }
    }
}
