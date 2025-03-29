package com.rvcode.e_service

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.views.AddNewServideTypeScreen
import com.rvcode.e_service.views.ElectricianHomeScreen
import com.rvcode.e_service.views.HomeScreen
import com.rvcode.e_service.views.RegistrationScreen

import com.rvcode.e_service.views.RoleScreen
import com.rvcode.e_service.views.SignInScreen
import com.rvcode.e_service.views.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Destination.SplashScreen
    ){
        composable<Destination.SplashScreen> {
            SplashScreen(navController)
        }
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

        composable<Destination.AddNewServiceTye> {
            AddNewServideTypeScreen(navController)
        }
    }

}