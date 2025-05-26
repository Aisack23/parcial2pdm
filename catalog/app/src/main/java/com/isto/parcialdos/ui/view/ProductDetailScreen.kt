package com.isto.parcialdos.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.isto.parcialdos.ui.model.Order
import com.isto.parcialdos.ui.model.Products
import com.isto.parcialdos.ui.model.products
import com.isto.parcialdos.ui.viewmodel.OrderViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavController,
    orderViewModel: OrderViewModel
) {
    val product = products.firstOrNull { it.id == productId }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { paddingValues ->
            product?.let { prod ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(prod.name, style = MaterialTheme.typography.headlineLarge)
                    Spacer(Modifier.height(12.dp))
                    Image(
                        painter = rememberAsyncImagePainter(prod.imgUrl),
                        contentDescription = prod.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(prod.description)
                    Spacer(Modifier.height(24.dp))
                    Button(onClick = {
                        val newOrder = Order(
                            id = prod.id,
                            name = prod.name,
                            categories = prod.categories,
                            price = prod.price.toFloat(),
                            description = prod.description,
                            imgUrl = prod.imgUrl,
                            addedToCart = true
                        )
                        orderViewModel.addOrder(newOrder)
                        scope.launch {
                            snackbarHostState.showSnackbar("${prod.name} Added to shopping cart")
                        }
                    }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "cart")
                        Spacer(Modifier.width(8.dp))
                        Text("Add to shopping cart")
                    }
                }
            } ?: Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No exists")
            }
        }
    )
}
