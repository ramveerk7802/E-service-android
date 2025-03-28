package com.rvcode.e_service.viewModels

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.e_service.models.User
import com.rvcode.e_service.repositories.UserRepository
import com.rvcode.e_service.utility.MyRole
import kotlinx.coroutines.launch

class UserViewModel :ViewModel() {

    private val repository = UserRepository()
    private val _isProcess = MutableLiveData<Boolean>(false)
    private val _myRole = MutableLiveData<String?>()


    val isProcess :LiveData<Boolean> = _isProcess
    val myRole:LiveData<String?> = _myRole


    init {
        viewModelScope.launch {
            _isProcess.postValue(true)
            val result = repository.checkRole()
            result?.let {
                _myRole.postValue(it)
            }
            _isProcess.postValue(false)
        }
    }



    fun registration(name:String, email: String,phone:String,password:String, address:String,city:String,state:String,pinCode:String,aadhaarNumber:String?,aadhaarImageUri:String,
                     role:MyRole,
                     onSuccess:()->Unit,
                     onFailure:(String)->Unit
                     ){
        viewModelScope.launch {
            _isProcess.postValue(true)
            if (!repository.validateEmail(email)){
                _isProcess.postValue(false)
                onFailure("Enter the valid email")
            }

            else if(!repository.validationPhoneNumber(phone)){
                _isProcess.postValue(false)
                onFailure("Enter the valid Phone number")
        }
            else if(!repository.validatePassword(password = password)){
                _isProcess.postValue(false)
                onFailure("Password length must 8 character")

            }
            else if(role==MyRole.ELECTRICIAN && !repository.validateAadhaarNumber(aadhaarNumber!!)){
                _isProcess.postValue(true)
                onFailure("Enter the valid Aadhaar Number")
            }

            else{
                val result = repository.createNewRegistration(name = name, email= email, password = password,
                    phone = phone,
                    address = address,
                    city = city,
                    state = state,
                    pinCode = pinCode,
                    aadhaarNumber = aadhaarNumber,
                    aadhaarImageUri = aadhaarImageUri,
                    role = role
                    )
                if(result==null){
                    onFailure("Failed to Creating Account try again after sometime.")
                    _isProcess.postValue(false)
                }else{
                    _isProcess.postValue(false)
                    onSuccess()
                }
            }


        }
    }
}