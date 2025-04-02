package com.rvcode.e_service.utilityCompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

import com.rvcode.e_service.R
import com.rvcode.e_service.utility.CustomerBottomNavigation


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


@Composable
fun MyInputTextField(state:MutableState<String>,label:String,placeHolder:String,leadingIcon:Int){
    OutlinedTextField(
        value = state.value ,
        onValueChange = {state.value=it},
        label = {
            Text(
                text = label
            )
        },
        placeholder = {
            Text(
                text = placeHolder
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = ""
            )
        },
        singleLine = true
    )

}

@Composable
fun MyInputTextField(state:MutableState<String>,label:String,placeHolder:String,leadingIcon:ImageVector){

    OutlinedTextField(
        value = state.value ,
        onValueChange = {state.value=it},
        label = {
            Text(
                text = label
            )
        },
        placeholder = {
            Text(
                text = placeHolder
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = ""
            )
        },
        singleLine = true
    )
}

@Composable
fun MyPasswordInputField(state: MutableState<String>, label: String,placeHolder: String){
    var isVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = state.value,
        onValueChange = {state.value=it},
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = ""
            )
        },
        label = {
            Text(text = label)
        },
        placeholder = { Text( text = placeHolder) },

        visualTransformation = if(isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = {
                    isVisible=!isVisible
                }
            ){
                Icon(
                    painter = painterResource( if(isVisible) R.drawable.eye_open else R.drawable.eye_closed),
                    contentDescription = ""
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        supportingText = {
            Text(
                text = "Password must contain: 1 uppercase, 1 lowercase, 1 number, and 1 special character (@#\$%^&+=!)",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    )
}


@Composable
fun LoadingDialog(text:String){
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {},
        text = {
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CircularProgressIndicator()
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                Text(
                    text = text
                )
            }
        }

    )
}

@Composable
fun ProfileHeader(imageUrl: String, name: String, email: String, onEditClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC)) // Purple to Blue Gradient
                )
            )
            .padding(16.dp)
    ) {
        Column {
            // Edit Button at the top right
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit Profile",
                        tint = Color.White
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = imageUrl.ifBlank { R.drawable.logo },
                    contentDescription = "User Profile",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = email,
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}


