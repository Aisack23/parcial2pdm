package com.isto.parcialdos.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.isto.parcialdos.ui.model.Products

@Composable
fun ProductCategorySection(
    title: String,
    productsList: List<Products>,
    navController: NavController
) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(8.dp))

        LazyRow!!(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(productsList) { product ->
                Card(
                    modifier = Modifier
                        .width(180.dp)
                        .padding(end = 12.dp)
                        .clickable {
                            navController.navigate("productDetail/${product.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(product.imgUrl),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                        )
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
