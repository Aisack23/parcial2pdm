package com.isto.parcialdos.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.isto.parcialdos.ui.model.Products
import com.isto.parcialdos.ui.model.products

class SearchViewModel : ViewModel() {
    private val allProducts: List<Products> = products

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _results = MutableStateFlow(allProducts)
    val results: StateFlow<List<Products>> = _results

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
        _results.value = if (newQuery.isBlank()) {
            allProducts
        } else {
            allProducts.filter { product ->
                product.name.contains(newQuery, ignoreCase = true)
            }
        }
    }
}
