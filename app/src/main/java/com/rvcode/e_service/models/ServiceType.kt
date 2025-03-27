package com.rvcode.e_service.models

data class ServiceType(
    val electricianId:String="",
    val serviceTypeId:String="",
    val serviceName:String="",
    val baseCharge:Double=0.0,
    val description:String=""
)
