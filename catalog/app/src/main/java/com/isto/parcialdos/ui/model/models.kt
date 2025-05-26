package com.isto.parcialdos.ui.model

//productos
data class Products(
    val id:Int,
    val name: String,
    val categories: List<String>,
    val price: Double,
    val description: String,
    val addedToCart: Boolean,
    val imgUrl: String?
)

//categories
val categories = products.flatMap { it.categories }.distinct()

data class Order(
    val id:Int,
    val name: String,
    val categories: List<String>,
    val price: Float,
    val description: String,
    val addedToCart: Boolean,
    val imgUrl: String?
)
