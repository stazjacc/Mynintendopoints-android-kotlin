package com.teste.jacunha.mynintendopoints

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.form_account.view.*

class AccountDialog(private val viewGroup: ViewGroup,
                    private val context: Context){

    private val createdView = createView()
    private val usernameField = createdView.form_account_username
    private val countryField = createdView.form_account_country
    private val gpointField = createdView.form_account_gpoints
    private val ppointField = createdView.form_account_ppoint

    fun alter(account: Account, altered: (alteredNote: Account) -> Unit) {
        usernameField.setText(account.username)
        countryField.setText(account.country)
        gpointField.setText(account.gpoints.toString())
        ppointField.setText(account.spoints.toString())

        AlertDialog.Builder(context)
            .setTitle("Alter Account")
            .setView(createdView)
            .setPositiveButton("Save"){_, _ ->
                val username = usernameField.text.toString()
                val country = countryField.text.toString()
                val gpoints = gpointField.text.toString()
                val spoints = ppointField.text.toString()
                val alteredAccount = account.copy(username = username, country = country, gpoints = gpoints.toInt(), spoints = spoints.toInt() )

                AccountWebClient().alter(alteredAccount, {
                    altered(it)},{
                    Toast.makeText(context, "Falha ao alterar nota", Toast.LENGTH_LONG).show()
                })
            }
            .show()
    }

    fun add(created: (createdAccount: Account) -> Unit){

        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.form_account, viewGroup, false)

        android.support.v7.app.AlertDialog.Builder(context)
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
                            created(account)
                        }
                    })
                }
            })
            .show()
    }

    private fun createView(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_account,
                viewGroup,
                false)
    }
}