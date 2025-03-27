package com.rvcode.e_service.models

data class Electrician(
    var aadhaarNumber: String="",
    val aadhaarImageUrl:String="",
    val serviceTypeList:MutableList<ServiceType> = mutableListOf()
)
