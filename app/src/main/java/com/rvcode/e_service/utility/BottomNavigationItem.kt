package com.rvcode.e_service.utility

import com.rvcode.e_service.R


sealed class BottomNavigationItem(
     val title:String,
     val icon: Int
 ) {
    object Home:BottomNavigationItem(
        title = "Home",
        icon = R.drawable.home
    )

    object Upcoming : BottomNavigationItem(
        title = "Upcoming",
        icon = R.drawable.bag
    )
    object History : BottomNavigationItem(
        title = "History",
        icon = R.drawable.history_icon
    )
    companion object{
        val items = listOf(Home,Upcoming,History)
    }
}