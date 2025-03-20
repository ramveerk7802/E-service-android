package com.rvcode.e_service.utilityCompose

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun MyOutlinedButton(title:String,onClick:()->Unit){

    OutlinedButton(
        onClick = {
            onClick()
        }
    ){
        Text(
            text = title
        )
    }

}