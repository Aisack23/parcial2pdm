package com.isto.parcialdos.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun CatalogBottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val items = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Search,
        Icons.Outlined.ShoppingCart
    )
    //rutas
    val routes = listOf("home", "search", "orders")
    //labels
    val labels = listOf("home", "search", "orders")

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, icon ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = labels[index],
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(labels[index]) },
                selected = currentRoute == routes[index],
                onClick = {
                    if (currentRoute != routes[index]) {
                        navController.navigate(routes[index]) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}