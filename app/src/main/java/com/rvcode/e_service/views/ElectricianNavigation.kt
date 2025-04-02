package com.rvcode.e_service.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.rvcode.e_service.R
import com.rvcode.e_service.utility.BottomNavigationItem
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.utility.NavigationItem
import com.rvcode.e_service.utilityCompose.ProfileHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectricianNavigation(navHostController: NavHostController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable{ mutableIntStateOf(0) }
    val showFloatingButton = remember { mutableStateOf(false) }

    val electricianNavHostController = rememberNavController()
    val titleIndex = remember { mutableIntStateOf(0) }

    val topBarTitle = when(titleIndex.intValue){
        0 -> "Home"
        1->"Up Comming"
        2-> "History"
        else->"Home"
    }


    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {

                ProfileHeader(
                    name = "Ramveer",
                    email = "ramveer@gamil.com",
                    imageUrl = ""
                ){
                    scope.launch {
                        drawerState.close()
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                NavigationItem.items.forEachIndexed{index, navigationItem ->
                    NavigationDrawerItem(
                        label = { Text(
                            text = navigationItem.title
                        ) },
                        onClick = {
                            selectedItemIndex=index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        selected = selectedItemIndex==index,
                        icon = {
                            Icon(
                                painter = painterResource(navigationItem.icon),
                                contentDescription = navigationItem.title
                            )
                        },
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
                        Text(text = topBarTitle)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ){
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = ""
                            )
                        }
                    }
                )
            },

            floatingActionButton = {
                if(showFloatingButton.value){
                    FloatingActionButton(
                        onClick = {
                            navHostController.navigate(Destination.AddEditNewServiceTye())
                        },
                        shape = CircleShape
                    ){
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "",
                        )
                    }
                }
            },
            bottomBar = {
                BottomAppBar{
                   MyBottomBar(electricianNavHostController)
                }
            }
        ){
            Box(
                modifier = Modifier.padding(it)
            ){
                NavHost(
                    navController = electricianNavHostController,
                    startDestination = BottomNavigationItem.ElectricianHome.route

                ){
                    composable(route = BottomNavigationItem.ElectricianHome.route){
                        showFloatingButton.value=true
                        titleIndex.intValue =0
                        ElectricianHomeScreen(navHostController)
                    }
                    composable(BottomNavigationItem.Upcoming.route){
                        showFloatingButton.value=false
                        titleIndex.intValue =1
                        UpComingScreen(electricianNavHostController)
                    }
                    composable(BottomNavigationItem.History.route){
                        showFloatingButton.value=false
                        titleIndex.intValue =2
                        HistoryScreen(electricianNavHostController)
                    }

                }

            }
        }

    }

}



@Composable
fun MyBottomBar(electricianNavHostController: NavHostController) {


    val navBackStack by electricianNavHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStack?.destination

    NavigationBar {
        BottomNavigationItem.items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route==bottomNavigationItem.route }==true,
                onClick = {
                    if(currentDestination?.route!=bottomNavigationItem.route){
                        electricianNavHostController.navigate( route = bottomNavigationItem.route){
                            popUpTo(electricianNavHostController.graph.startDestinationId){
                                saveState =true
                            }
                            launchSingleTop =true
                            restoreState =true

                        }
                    }
                },
                label = { Text( text = bottomNavigationItem.title) },
                icon = { Icon(
                    painter = painterResource(bottomNavigationItem.icon),
                    contentDescription = bottomNavigationItem.title
                ) }
            )
        }
    }

}




