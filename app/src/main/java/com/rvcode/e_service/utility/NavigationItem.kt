package com.rvcode.e_service.utility

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.rvcode.e_service.R

sealed class NavigationItem(
    val title: String,
    val icon: Int
) {
    object Home : NavigationItem(
        title = "Home",
        icon = R.drawable.home
    )
    object Share:NavigationItem(
        title = "Share",
        icon = R.drawable.share
    )

    object PrivacyPolicy : NavigationItem(
        title = "Privacy & Policy",
        icon = R.drawable.privacy
    )

    object Logout : NavigationItem(
        title = "Logout",
        icon = R.drawable.logout
    )



    companion object {
        val items = listOf(Home, Share,PrivacyPolicy,Logout)
    }
}