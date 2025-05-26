package com.isto.parcialdos.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.isto.parcialdos.ui.component.CatalogBottomBar
import com.isto.parcialdos.ui.component.CatalogTopBar
import com.isto.parcialdos.ui.viewmodel.OrderViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    orderViewModel: OrderViewModel
) {
    val orders = orderViewModel.orders.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = { CatalogTopBar() },
        bottomBar = { CatalogBottomBar(navController = navController as NavHostController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Shopping Cart",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (orders.isEmpty()) {
                    Text("No products added")
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(orders) { item ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(item.imgUrl),
                                        contentDescription = item.name,
                                        modifier = Modifier.size(64.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(Modifier.weight(1f)) {
                                        Text(item.name, fontWeight = FontWeight.Bold)
                                    }
                                    IconButton(
                                        onClick = { orderViewModel.removeOrder(item) }
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
