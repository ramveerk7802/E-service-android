package com.rvcode.e_service.utility

import kotlinx.serialization.Serializable



@Serializable
sealed class Destination {

    @Serializable
     data object RoleScreen:Destination()


    @Serializable
    data class SignIn(val role:MyRole?=null):Destination()


    @Serializable
    data class Registration(val role:MyRole?=null):Destination()
}