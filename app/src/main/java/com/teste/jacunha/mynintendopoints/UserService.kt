package com.teste.jacunha.mynintendopoints

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

class UserService {
    @POST("api/v1/auth/sign_in")
    fun insert(@Body user: User) {
    }
}