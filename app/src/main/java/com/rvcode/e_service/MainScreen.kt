package com.rvcode.e_service

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MainScreen(){

    val navController = rememberNavController();
    NavigationGraph(navController)

}