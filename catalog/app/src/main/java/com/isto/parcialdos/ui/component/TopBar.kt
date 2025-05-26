package com.isto.parcialdos.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogTopBar(
) {
    TopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Catalog")
                    }
                },
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = Modifier,
        scrollBehavior = null
    )
}
