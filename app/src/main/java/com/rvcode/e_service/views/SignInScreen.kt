package com.rvcode.e_service.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.twotone.Build
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.rvcode.e_service.R
import com.rvcode.e_service.utility.Destination
import com.rvcode.e_service.utility.MyRole
import com.rvcode.e_service.utilityCompose.LoadingDialog
import com.rvcode.e_service.utilityCompose.MyInputTextField
import com.rvcode.e_service.utilityCompose.MyOutlinedButton
import com.rvcode.e_service.utilityCompose.MyPasswordInputField
import com.rvcode.e_service.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navHostController: NavHostController) {
    val viewModel:UserViewModel = viewModel()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Sign in")
            }

        )
    }) {
        App(Modifier.padding(it), navHostController,viewModel)
    }


}

@Composable
private fun App(modifier: Modifier, navHostController: NavHostController,viewModel: UserViewModel) {
    val isProcess = viewModel.isProcess.observeAsState(false).value
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val context = LocalContext.current



    Column(
        modifier = modifier
            .imePadding()
            .padding(20.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        AsyncImage(
            model = R.drawable.logo,
            contentDescription = "Banner",
        )

        MyInputTextField(emailState,"Email","example@gmail.com",Icons.Outlined.Email)
        MyPasswordInputField(passwordState,"Password","Password")

        Text(
            modifier = Modifier.align(Alignment.End),
            text = "Forget password",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MyOutlinedButton("Sign in") {
                if(emailState.value.isBlank() || passwordState.value.isBlank()){
                    Toast.makeText(context,"All fields are required.",Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.signInWithEmailAndPassword(email = emailState.value, password = passwordState.value, onSuccess = {
                        navHostController.navigate(Destination.SplashScreen){
                            popUpTo<Destination.SplashScreen>{
                                inclusive =true
                            }
                        }
                    },
                        onFailure = {
                            Toast.makeText(context,"Failed to login : $it",Toast.LENGTH_SHORT).show()

                        })
                }


            }
            MyOutlinedButton("Registration") {
                navHostController.navigate(Destination.RoleScreen)
            }
        }

        if(isProcess){
            LoadingDialog(text = "Processing...")
        }


    }
}
