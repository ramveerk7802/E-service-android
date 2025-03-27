package com.rvcode.e_service.models

import com.rvcode.e_service.utility.ServiceStatus

data class ServiceRequest(
    val requestId:String="",
    val userId:String="",
    val electricianId:String="",
    val serviceTypeId:String="",
    val serviceStatus: ServiceStatus = ServiceStatus.PENDING
)
