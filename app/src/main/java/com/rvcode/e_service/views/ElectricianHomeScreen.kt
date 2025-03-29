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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.rvcode.e_service.R
import com.rvcode.e_service.utility.BottomNavigationItem
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.utility.NavigationItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectricianHomeScreen(navHostController: NavHostController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable{ mutableIntStateOf(0) }
    val navHostController2 = rememberNavController()


    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp)
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
                        Text(text = "Dashboard")
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
                FloatingActionButton(
                    onClick = {
                        navHostController.navigate(Destination.AddNewServiceTye)
                    },
                    shape = CircleShape
                ){
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "",
                    )
                }
            },
            bottomBar = {
                BottomAppBar{
                   MyBottomBar(navHostController)
                }
            }
        ){
            Content(Modifier.padding(it))
        }

    }

}

@Composable
fun MyBottomBar(navHostController: NavHostController) {

    NavigationBar {
        BottomNavigationItem.items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = true,
                onClick = {},
                label = { Text( text = bottomNavigationItem.title) },
                icon = { Icon(
                    painter = painterResource(bottomNavigationItem.icon),
                    contentDescription = bottomNavigationItem.title
                ) }
            )
        }
    }

}


@Composable
fun ProfileHeader(imageUrl: String, name: String, email: String, onEditClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC)) // Purple to Blue Gradient
                )
            )
            .padding(16.dp)
    ) {
        Column {
            // Edit Button at the top right
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit Profile",
                        tint = Color.White
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = imageUrl.ifBlank { R.drawable.logo },
                    contentDescription = "User Profile",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = email,
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}


@Composable
private fun Content(modifier: Modifier){

}