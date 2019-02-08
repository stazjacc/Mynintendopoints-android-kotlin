package com.teste.jacunha.mynintendopoints

import retrofit2.Call
import retrofit2.http.*

interface AccountService {
    @GET("api/v1/accounts")
    fun list() : Call<List<Account>>

    @POST("api/v1/accounts")
    fun insert(@Body account: Account) : Call<Account>

    @PUT("api/v1/accounts/{id}")
    fun alter(@Body account: Account, @Path("id") id: Int) : Call<Account>
}