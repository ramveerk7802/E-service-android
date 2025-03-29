package com.rvcode.e_service.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.rvcode.e_service.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewServideTypeScreen(navHostController: NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text( text = "Add")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ){
                        Icon(
                            painter = painterResource(R.drawable.back_arrow),
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ){
        MyContent(Modifier.padding(it),navHostController)
    }
}

@Composable
fun MyContent(modifier: Modifier,navHostController: NavHostController){

}