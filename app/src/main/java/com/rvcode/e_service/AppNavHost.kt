package com.rvcode.e_service

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rvcode.e_service.utility.BottomNavigationItem
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.views.AddEditNewServideTypeScreen

import com.rvcode.e_service.views.CustomerNavigation
import com.rvcode.e_service.views.ElectricianHomeScreen
import com.rvcode.e_service.views.ElectricianNavigation

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
        composable<Destination.CustomerNavigation> {
            CustomerNavigation(navController)
        }
        composable<Destination.ElectricianNavigation> {
            ElectricianNavigation(navController)
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

        composable<Destination.AddEditNewServiceTye> {
            val args = it.toRoute<Destination.AddEditNewServiceTye>()
            val isEdit = args.isEdit
            AddEditNewServideTypeScreen(navController, isEdit = isEdit)
        }

    }

}