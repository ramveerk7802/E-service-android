package com.rvcode.e_service.views

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
fun RegistrationScreen(navHostController: NavHostController, role: MyRole?) {
    val viewModel:UserViewModel = viewModel()
    val titleText = if (role == MyRole.CUSTOMER) "Customer" else "Electrician"
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "$titleText registration"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_arrow),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        App(Modifier.padding(it), navHostController, role,viewModel)
    }
}


@Composable
private fun App(modifier: Modifier, navHostController: NavHostController, role: MyRole?,viewModel: UserViewModel) {

    val context = LocalContext.current

    val isProcess = viewModel.isProcess.observeAsState(false).value

    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val phoneState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val cityState = remember { mutableStateOf("") }
    val state = remember { mutableStateOf("") }
    val pinCodeState = remember { mutableStateOf("") }
    val aadhaarNumberState = remember { mutableStateOf("") }
    val aadhaarImageUri = remember { mutableStateOf("") }

    val addressState = remember { mutableStateOf("") }

    val isModalBottomSheet = remember { mutableStateOf(false) }


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ){uri->
        uri?.let {
            aadhaarImageUri.value=it.toString()
        }
    }






    Column(
        modifier = modifier.padding(20.dp)
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        MyInputTextField(state = nameState, label = "Name", placeHolder = "Enter name", leadingIcon = Icons.Outlined.Person)

        MyInputTextField(state = emailState, label = "Email", placeHolder = "Enter email", leadingIcon = Icons.Outlined.Email)

        MyInputTextField(state = phoneState, label = "Mobile","Mobile", leadingIcon = Icons.Outlined.Phone)

        MyPasswordInputField(state = passwordState, label = "Password", placeHolder = "Password")

        MyInputTextField(state = addressState, label = "Address/Landmark", placeHolder = "House no, Street", leadingIcon = Icons.Outlined.Place)

        MyInputTextField(state = cityState, label = "City", placeHolder = "City name", leadingIcon = Icons.Outlined.Place)

        MyInputTextField(state = state, label = "State", placeHolder = "State name", leadingIcon = Icons.Outlined.Place)

        MyInputTextField(state = pinCodeState, label = "Zip/Pin Code", placeHolder = "Enter 6 digit Code", leadingIcon = Icons.Outlined.Place)

        if(role==MyRole.ELECTRICIAN) {
            MyInputTextField(state = aadhaarNumberState, label = "Aadhaar Number", placeHolder = "XXXXXXXX1234", leadingIcon = R.drawable.maadhaar)
            Text(
                text = "Upload Aadhaar card Image",
                modifier = Modifier.align(Alignment.Start),
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )

            Box (
                modifier = Modifier.fillMaxWidth()
                    .height(200.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center

            ){
                AsyncImage(
                    model = if(aadhaarImageUri.value.isBlank() || aadhaarImageUri.value.isEmpty()) R.drawable.upload else aadhaarImageUri.value,
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.matchParentSize()
                        .clickable {
                            isModalBottomSheet.value = !isModalBottomSheet.value
                        }

                )
            }
        }

        MyOutlinedButton(title = "Register"){
            val currentRole:MyRole
            if(role==MyRole.CUSTOMER)
                currentRole = MyRole.CUSTOMER
            else
                currentRole = MyRole.ELECTRICIAN
                createNewRegistration(
                    context,
                    nameState.value,
                    emailState.value,
                    phoneState.value,
                    passwordState.value,
                    addressState.value,
                    cityState.value,
                    state.value,
                    pinCodeState.value,
                    aadhaarNumberState.value,
                    aadhaarImageUri.value,
                    currentRole,
                    viewModel,
                    onSuccess = {
                        navHostController.navigate(Destination.Home){
                            popUpTo<Destination.Home>{
                                inclusive=true
                            }
                        }

                    }
                ){

                    Toast.makeText(context,"Error : -> $it",Toast.LENGTH_SHORT).show()

                }

        }

        if(isModalBottomSheet.value){
            MyBottomSheet(
                isModalBottomSheet= isModalBottomSheet,
                onClickGallery = {
                    galleryLauncher.launch(input = PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    Toast.makeText(context,"Clicked on Gallery Button",Toast.LENGTH_SHORT).show()
                    isModalBottomSheet.value=false
            },
                onClickCamera = {
                    Toast.makeText(context,"Clicked on Camera Button",Toast.LENGTH_SHORT).show()
                    isModalBottomSheet.value=false

            })
        }

        if(isProcess){
            LoadingDialog(text = "Creating account... please wait")
        }




    }
}

fun createNewRegistration(
    context:Context,
    name: String,
    email: String,
    phone: String,
    password: String,
    address: String,
    city: String,
    state: String,
    pinCode: String,
    aadhaarNumber: String,
    aadhaarImageUri: String,
    role: MyRole,
    viewModel: UserViewModel,
    onSuccess: () -> Unit,
    onFailure:(String)->Unit
) {
    if(name.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank() || address.isBlank() || city.isBlank() || state.isBlank() ||pinCode.isBlank()){
        Toast.makeText(context,"All field are required.",Toast.LENGTH_SHORT).show()
    }
    else if(role==MyRole.ELECTRICIAN && aadhaarNumber.isBlank() || aadhaarImageUri.isBlank()){
        Toast.makeText(context,"All field are required.",Toast.LENGTH_SHORT).show()
    }else{
        viewModel.registration(name = name, email = email,phone=phone,password = password, address = address,city=city,state = state, pinCode =  pinCode, aadhaarNumber = aadhaarNumber, aadhaarImageUri = aadhaarImageUri, role = role, onSuccess = {
            onSuccess()
        }){
            onFailure(it)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(isModalBottomSheet:MutableState<Boolean>,onClickGallery:()->Unit,onClickCamera:()->Unit){
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { isModalBottomSheet.value=false},
        sheetState = sheetState
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            MyCustomView(label = "Gallery", icon = R.drawable.gallery_icon){
                onClickGallery()
            }
            MyCustomView(label = "Camera", icon = R.drawable.camera_icon){
                onClickCamera()
            }
        }

    }

}



@Composable
private fun MyCustomView(label:String,icon:Int,onClick:()->Unit){

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                onClick()
            }
        ){
            Icon(
                painter = painterResource(icon),
                contentDescription = label
            )
        }
        Text(text = label)
    }
}