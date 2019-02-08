package com.teste.jacunha.mynintendopoints

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountWebClient {

    fun list(callbackResponse : CallbackResponse<List<Account>>){
        val call = RetrofitInitializer().accountService().list()

        call.enqueue(object: Callback<List<Account>?> {
            override fun onFailure(call: Call<List<Account>?>, t: Throwable) {
                Log.e("onFailure error", t.message)
            }

            override fun onResponse(call: Call<List<Account>?>?,
                response: Response<List<Account>?>
            ) {
                response.body()?.let {
                    val accounts : List<Account> = it
                    //configureList(accounts)
                    callbackResponse.success(accounts)
                }
            }
        })
    }

    fun insert(account: Account, callbackResponse: CallbackResponse<Account>){
        val call = RetrofitInitializer().accountService().insert(account)

        call.enqueue(object: Callback<Account?>{
            override fun onFailure(call: Call<Account?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                response.body()?.let {
                    val accouts: Account = it
                    callbackResponse.success(accouts)
                }
            }

        })
    }


    fun alter(account: Account, success: (account: Account) -> Unit,
              failure: (throwable: Throwable) -> Unit){
        val call = RetrofitInitializer().accountService().alter(account, account.id)

        call.enqueue(object: Callback<Account?>{

            override fun onFailure(call: Call<Account?>?, t: Throwable?) {
                t?.let{
                    failure(it)
                }
                //Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                response.body()?.let {
                    val accouts: Account = it
                    success(accouts)
                }
            }
        })
    }
}