package com.rvcode.e_service.utility

import com.rvcode.e_service.R

sealed class CustomerBottomNavigation(
    val title:String,
    val route:String,
    val icon:Int
) {
    object Home:CustomerBottomNavigation(
        title = "Home",
        route = "home",
        icon = R.drawable.home
    )
    object Cart:CustomerBottomNavigation(
        title = "Cart",
        route = "cart",
        icon = R.drawable.cart
    )
    object Order:CustomerBottomNavigation(
        title = "Order",
        route = "order",
        icon = R.drawable.order
    )
    companion object{
        val items = listOf(Home,Cart,Order)
    }
}