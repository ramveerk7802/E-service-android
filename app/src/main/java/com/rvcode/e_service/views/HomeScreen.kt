package com.rvcode.e_service.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.utility.MyRole
import com.rvcode.e_service.utilityCompose.LoadingDialog
import com.rvcode.e_service.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController){

    val userViewModel:UserViewModel= viewModel()
    val myRole = userViewModel.myRole.observeAsState().value
    val currentUser = Firebase.auth.currentUser

    if(currentUser==null){
        LaunchedEffect(key1 = Unit){
            navHostController.navigate(Destination.SignIn)
        }
    }else{

        LaunchedEffect(myRole){
            myRole?.let {
                if(myRole==MyRole.ELECTRICIAN.name){
                    navHostController.navigate(Destination.ElectricianHome){
                        popUpTo<Destination.Home>{
                            inclusive=true
                        }
                    }
                }
            }

        }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text("Home Screen")
                    }
                )
            }
        ) {
            App(Modifier.padding(it),navHostController,userViewModel)
        }
    }

}

@Composable
private fun App(modifier: Modifier, navHostController: NavHostController,userViewModel: UserViewModel) {

    val isProcess = userViewModel.isProcess.observeAsState(false).value


    Column (
        modifier= modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center
        ){

        if(isProcess){
            LoadingDialog(text = "")
        }

    }

}
