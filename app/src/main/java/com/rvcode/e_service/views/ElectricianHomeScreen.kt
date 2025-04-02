package com.rvcode.e_service.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rvcode.e_service.utility.Destination


@Composable
fun ElectricianHomeScreen(navHostController: NavHostController){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()) {
        items(10) {  // Repeats MyView 10 times
            MyView(){
                navHostController.navigate(Destination.AddEditNewServiceTye(isEdit = true))
            }
        }
    }
}


@Composable
fun MyView(onclick:()->Unit){
    Box(modifier = Modifier.fillMaxWidth()
        .height(120.dp)
        .background(
            color = Color.Red,
            shape = RoundedCornerShape(10.dp)
        )
        .clickable {
            onclick()
        },
        contentAlignment = Alignment.Center){
        Text(text = "Electrician Screen")
    }
}