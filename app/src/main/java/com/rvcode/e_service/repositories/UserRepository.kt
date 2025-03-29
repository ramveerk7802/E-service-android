package com.rvcode.e_service.repositories

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.rvcode.e_service.models.Electrician
import com.rvcode.e_service.models.User
import com.rvcode.e_service.utility.MyRole
import kotlinx.coroutines.tasks.await
import okhttp3.Address

class UserRepository {

    private  val auth by lazy {
        Firebase.auth
    }
    private val firestoreRef by lazy {
        Firebase.firestore
    }

    suspend fun createNewRegistration(
        name:String,
        email:String,
        password:String,
        phone:String,
        address:String,
        city:String,
        state:String,
        pinCode:String,
        aadhaarNumber:String?,
        aadhaarImageUri:String?,
        role:MyRole
        ):FirebaseUser?{
        try {
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            result.user?.let {
                val electrician = if (role == MyRole.ELECTRICIAN) {
                    Electrician(
                        aadhaarNumber = aadhaarNumber ?: "",
                        aadhaarImageUrl = "AADHAAR IMAGE URL"  // Placeholder as Aadhaar image is not stored
                    )
                } else null
                val userData = User(
                    userId = it.uid,
                    name = name,
                    email = email,
                    phone = phone,
                    password = password,
                    pinCode = pinCode,
                    city = city,
                    address = address,
                    state = state,
                    electrician = electrician?: Electrician(),
                    role = role
                )

                val userCollectionsRef = firestoreRef.collection("users")
                userCollectionsRef.document(it.uid).set(userData).await()

            }
            return result.user

        }catch (e:Exception){
            return null
        }
    }

    suspend fun signInWithEmailAndPassword(email: String,password: String):FirebaseUser?{
       return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            result.user
        }catch (e:Exception){
            null
        }
    }


    suspend fun checkRole():String?{
        return try {
            auth.currentUser?.let {
                val userRef = firestoreRef.collection("users")
                val doc = userRef.document(it.uid).get().await()
                doc.getString("role")
            }

        }catch (e:Exception){
             null
        }
    }

    suspend fun validationPhoneNumber(phone:String):Boolean{
        return phone.length==10 && phone.all { it.isDigit() }
    }
    suspend fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return email.matches(emailRegex)
    }
    suspend fun validatePinCode(code:String):Boolean{
        val regex  = Regex("^[0-9]{6}$")
        return code.matches(regex)
    }

    suspend fun validatePassword(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$")
        return password.matches(regex)
    }
    suspend fun validateAadhaarNumber(aadhaar:String):Boolean{
        val regex = Regex("^[0-9]{12}$")
        return aadhaar.matches(regex)
    }


}