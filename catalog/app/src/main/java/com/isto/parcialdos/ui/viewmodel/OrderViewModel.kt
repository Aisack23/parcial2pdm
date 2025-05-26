package com.isto.parcialdos.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.isto.parcialdos.ui.model.Order

class OrderViewModel : ViewModel() {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    fun addOrder(order: Order) {
        _orders.value = _orders.value + order
    }

    fun removeOrder(order: Order) {
        _orders.value = _orders.value - order
    }
}
