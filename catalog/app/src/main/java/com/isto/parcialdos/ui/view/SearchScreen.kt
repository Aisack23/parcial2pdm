package com.isto.parcialdos.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isto.parcialdos.ui.component.CatalogBottomBar
import com.isto.parcialdos.ui.component.CatalogTopBar
import com.isto.parcialdos.ui.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = viewModel()
) {
    val queryState = viewModel.query.collectAsState()
    val resultsState = viewModel.results.collectAsState()

    Scaffold(
        topBar = { CatalogTopBar() },
        bottomBar = { CatalogBottomBar(navController = navController as NavHostController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = queryState.value,
                onValueChange = viewModel::onQueryChanged,
                label = { Text("Search") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (queryState.value.isNotBlank()) {
                if (resultsState.value.isEmpty()) {
                    Text("No results")
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        item {
                            ProductCategorySection(
                                title = "Results",
                                productsList = resultsState.value,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
