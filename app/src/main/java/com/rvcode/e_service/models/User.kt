package com.rvcode.e_service.models

import com.rvcode.e_service.utility.MyRole

data class User(
    val userId:String="",
    val name:String="",
    val email:String="",
    val phone:String="",
    val password:String="",
    val address:String="",
    val city:String="",
    val pinCode:String="",
    val state:String="",
    val role: MyRole,
    val electrician: Electrician,
    val serviceRequestList: MutableList<ServiceRequest> = mutableListOf()
)
