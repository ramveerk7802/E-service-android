package com.rvcode.e_service

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.views.ElectricianHomeScreen
import com.rvcode.e_service.views.HomeScreen
import com.rvcode.e_service.views.RegistrationScreen

import com.rvcode.e_service.views.RoleScreen
import com.rvcode.e_service.views.SignInScreen

@Composable
fun NavigationGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ){
        composable<Destination.Home> {
            HomeScreen(navController)
        }
        composable<Destination.ElectricianHome> {
            ElectricianHomeScreen(navController)
        }
        composable<Destination.RoleScreen> {
                RoleScreen(navController)
            }
        composable<Destination.SignIn> {
            SignInScreen(navController)
        }
        composable<Destination.Registration> {
            val args = it.toRoute<Destination.Registration>()
            RegistrationScreen(navController,args.role)
        }
    }

}