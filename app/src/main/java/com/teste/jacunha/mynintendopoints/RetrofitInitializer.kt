package com.teste.jacunha.mynintendopoints

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

       private val retrofit =  Retrofit.Builder()
            .baseUrl("http://10.20.129.222:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun accountService() = retrofit.create(AccountService::class.java)
        fun UserService() = retrofit.create(UserService::class.java)

}