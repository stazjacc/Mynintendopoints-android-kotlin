package com.teste.jacunha.mynintendopoints

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.form_account.view.*

class AccountActivity : AppCompatActivity() {

    private val accounts : MutableList<Account> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        AccountWebClient().list(object : CallbackResponse<List<Account>> {
            override fun success(response: List<Account>) {
                configureList(response as MutableList<Account>)
            }
        })

        add_account.setOnClickListener {
            val createdView = LayoutInflater.from(this).inflate(R.layout.form_account,
                window.decorView as ViewGroup, false)

            AlertDialog.Builder(this)
                .setTitle("Adicionar Conta")
                .setView(createdView)
                .setPositiveButton("Save", object: DialogInterface.OnClickListener{

                    override fun onClick(dialog: DialogInterface?, which: Int){
                        val username = createdView.form_account_username.text.toString()
                        val country = createdView.form_account_country.text.toString()
                        val gpoint = Integer.parseInt(createdView.form_account_gpoints.text.toString())
                        val ppoint = Integer.parseInt(createdView.form_account_ppoint.text.toString())
                        val account = Account(username = username, country = country, gpoints = gpoint, spoints = ppoint)

                        AccountWebClient().insert(account, object: CallbackResponse<Account>{
                            override fun success(response: Account) {
                                this@AccountActivity.accounts.addAll(accounts)
                                configureList(accounts)
                            }
                        })
                    }
                })
                .show()
        }

        /*val recyclerView = account_list_recyclerview
        recyclerView.adapter = AccountListAdapter(accounts(), this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager*/
    }

    private fun configureList(accounts: MutableList<Account>){

        val recyclerView = account_list_recyclerview
        //recyclerView.adapter = AccountListAdapter(accounts, this)

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = AccountListAdapter(accounts, this){ account, position ->
            AccountDialog(window.decorView as ViewGroup, this).alter(account){
                accounts.set(position, it)
                configureList(accounts)
            }
            //Toast.makeText(this, "Clicado!", Toast.LENGTH_LONG).show()
        }
    }

    /*private fun accounts(): List<Account> {

        return listOf(

            Account("USA",
                100,
                450)
            )
    }*/
}
