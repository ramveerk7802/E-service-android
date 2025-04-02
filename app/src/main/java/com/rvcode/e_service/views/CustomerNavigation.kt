package com.rvcode.e_service.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rvcode.e_service.R
import com.rvcode.e_service.utility.CustomerBottomNavigation
import com.rvcode.e_service.utility.NavigationItem
import com.rvcode.e_service.utilityCompose.ProfileHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerNavigation(navHostController: NavHostController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by rememberSaveable{ mutableIntStateOf(0) }

    val customerNavHostController = rememberNavController()

    var titleIndex by remember { mutableIntStateOf(0) }
    val topBarTitle = when(titleIndex){
        0 -> "Home"
        1-> "Cart"
        2-> "Order"
        else-> "Home"

    }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                ProfileHeader(imageUrl = "", name = "Customer", email = "customer"){
                    scope.launch {
                        drawerState.close()
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                NavigationItem.items.forEachIndexed { index, navigationItem ->

                    NavigationDrawerItem(
                        label = {
                            Text(text = navigationItem.title)
                        },
                        onClick = {
                            scope.launch {
                                selectedItem=index
                                drawerState.close()
                            }
                        },
                        selected = selectedItem==index,
                        icon = { Icon(painter = painterResource(navigationItem.icon),
                            contentDescription = navigationItem.title) },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                }
            }
        },
        drawerState = drawerState
    ){
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = topBarTitle
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ){
                            Icon(imageVector = Icons.Outlined.Menu,
                                contentDescription = "menu")
                        }
                    }

                )
            },
            bottomBar = {
                BottomAppBar {
                    CustomerBottomBar(customerNavHostController)
                }
            }
        ){
            Box(
                modifier = Modifier.padding(it),
                contentAlignment = Alignment.Center
            ){
                NavHost(
                    navController = customerNavHostController,
                    startDestination = CustomerBottomNavigation.Home.route
                ){
                    composable(route = CustomerBottomNavigation.Home.route) {
                            titleIndex=0
                            CustomerHomeScreen(navHostController)
                    }
                    composable(route = CustomerBottomNavigation.Cart.route) {
                        titleIndex=1
                        CustomerCartScreen(navHostController)
                    }
                    composable(route = CustomerBottomNavigation.Order.route) {
                        titleIndex=2
                        CustomerOrderScreen(navHostController)
                    }
                }

            }
        }
    }


}


@Composable
fun CustomerBottomBar(customerNavHostController: NavHostController) {

    val  navBackStackEntry by customerNavHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        CustomerBottomNavigation.items.forEach {currentItem->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route== currentItem.route }==true,
                label = {
                    Text(text = currentItem.title)
                },
                icon = {
                    Icon(painter = painterResource(currentItem.icon),
                        contentDescription = currentItem.title)
                },
                onClick = {
                    if(currentDestination?.route!=currentItem.route){
                        customerNavHostController.navigate(route = currentItem.route){
                            popUpTo(customerNavHostController.graph.startDestinationId) {
                                saveState =true
                            }
                            launchSingleTop =true
                            restoreState = true
                        }
                    }
                }
            )

        }
    }

}

@Composable
private fun App(modifier: Modifier, navHostController: NavHostController) {



    Column (
        modifier= modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center
        ){


    }

}
