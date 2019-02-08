package com.teste.jacunha.mynintendopoints

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.account_item.view.*

class AccountListAdapter(private val accounts: MutableList<Account>,
                         private val context: Context,
                         private val onItemClickListener: (account: Account, position: Int) -> Unit) :
    Adapter<AccountListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.account_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val account = accounts[p1]

        p0.let{
            it.globo_logo
            it.country.text = account.country

            it.user_logo
            it.username.text = account.username

            it.golden_coins
            it.goldenPoint.text = account.gpoints.toString()

            it.silver_coins
            it.platinumPoint.text = account.spoints.toString()

            it.itemView.setOnClickListener {
                onItemClickListener(account, p1)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val typeface = Typeface.createFromAsset(itemView.context.assets, "fa-solid-900.ttf")

        val user_logo = itemView.user_logo.setTypeface(typeface)
        val username = itemView.account_item_username!!

        val globo_logo = itemView.globe_logo.setTypeface(typeface)
        val country = itemView.account_item_country!!

        val golden_coins = itemView.golden_coin_logo.setTypeface(typeface)
        val goldenPoint = itemView.account_item_golden_points!!

        val silver_coins = itemView.silver_coin_logo.setTypeface(typeface)
        val platinumPoint = itemView.account_item_platinum_points!!


        /*fun onClick(account: Account, execute: (account: Account) -> Unit){
            itemView.setOnClickListener{
                execute(account)
            }
        }*/
    }
}
