package com.rvcode.e_service.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.rvcode.e_service.R
import com.rvcode.e_service.utility.MyRole

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navHostController: NavHostController, role: MyRole?){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Registration"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ){
                        Icon(
                            painter = painterResource(R.drawable.back_arrow),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ){
        App(Modifier.padding(it),navHostController,role)
    }
}

@Composable
private fun App(modifier: Modifier,navHostController: NavHostController,role: MyRole?){
    
}