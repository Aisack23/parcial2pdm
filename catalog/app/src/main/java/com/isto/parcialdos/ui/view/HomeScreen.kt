package com.isto.parcialdos.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.isto.parcialdos.ui.component.CatalogBottomBar
import com.isto.parcialdos.ui.component.CatalogTopBar
import com.isto.parcialdos.ui.model.products


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val allProducts = products

    Scaffold(
        topBar = { CatalogTopBar() },
        bottomBar = { CatalogBottomBar(navController = navController as NavHostController) },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    ProductCategorySection(
                        title = "Our Products",
                        productsList = allProducts,
                        navController = navController
                    )
                }
            }
        }
    )
}
