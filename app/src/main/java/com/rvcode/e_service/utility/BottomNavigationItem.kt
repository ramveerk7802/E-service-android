package com.rvcode.e_service.utility

import com.rvcode.e_service.R


sealed class BottomNavigationItem(
     val title:String,
     val route:String,
     val icon: Int
 ) {
    object ElectricianHome:BottomNavigationItem(
        title = "Home",
        route = "home",
        icon = R.drawable.home
    )

    object Upcoming : BottomNavigationItem(
        title = "Upcoming",
        route= "upcoming",
        icon = R.drawable.bag
    )
    object History : BottomNavigationItem(
        title = "History",
        route= "history",
        icon = R.drawable.history_icon
    )
    companion object{
        val items = listOf(ElectricianHome,Upcoming,History)
    }
}