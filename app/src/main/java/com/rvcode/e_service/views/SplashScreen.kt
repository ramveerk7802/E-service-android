package com.rvcode.e_service.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.rvcode.e_service.R
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.utility.MyRole
import com.rvcode.e_service.viewModels.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()
    val myRole = userViewModel.myRole.observeAsState().value
    val currentUser = Firebase.auth.currentUser

    LaunchedEffect(myRole) {
        delay(1500) // Show splash screen for 2 seconds

        if (currentUser == null) {
            navController.navigate(Destination.SignIn) {
                popUpTo(Destination.SplashScreen) { inclusive = true }
            }
        } else {
            myRole?.let {currentRole->
                when (currentRole) {
                    MyRole.ELECTRICIAN.name -> {
                        navController.navigate(Destination.ElectricianNavigation) {
                            popUpTo(Destination.SplashScreen) { inclusive = true }
                        }
                    }
                    MyRole.CUSTOMER.name -> {
                        navController.navigate(Destination.CustomerNavigation) {
                            popUpTo(Destination.SplashScreen) { inclusive = true }
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Place your logo in res/drawable
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)// Adjust size as needed
        )
    }
}
