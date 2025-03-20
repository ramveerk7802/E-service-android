package com.rvcode.e_service.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.rvcode.e_service.R
import com.rvcode.e_service.ui.theme.Purple40
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.utility.MyRole

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Your Role"
                    )
                }
            )
        }
    ) {
        App(Modifier.padding(it), navController)
    }
}

@Composable
private fun App(modifier: Modifier, navController: NavHostController) {

    Column(
        modifier = modifier.padding(25.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = R.drawable.logo,
            contentDescription = "Banner",
            modifier = Modifier.fillMaxWidth()
                .height(220.dp)
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(
                    Destination.SignIn(MyRole.USER)
                ){
                    popUpTo<Destination.RoleScreen>{
                        inclusive=true
                    }
                }
            },

                border = BorderStroke(
                    width = 1.5.dp,
                    color = Purple40
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.customer_icon),
                contentDescription = "Customer icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Customer",
                style = TextStyle(
                    letterSpacing = 0.5.sp,
                    fontSize = 14.sp
                )
            )
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(
                    Destination.SignIn(MyRole.ELECTRICIAN)
                ){
                    popUpTo<Destination.RoleScreen>{
                        inclusive=true
                    }
                }
            },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Red
            ),
            border = BorderStroke(
                width = 1.5.dp,
                color = Color.Red
            )


        ) {
            Icon(
                painter = painterResource(R.drawable.electrician_icon),
                contentDescription = "Electrician icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Electrician",
                style = TextStyle(
                    letterSpacing = 0.5.sp,
                    fontSize = 14.sp
                )
            )
        }
    }
}

