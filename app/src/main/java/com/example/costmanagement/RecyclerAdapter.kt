package com.example.costmanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.invest_structure.view.*

class RecyclerAdapter(var investList: ArrayList<Companies>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.invest_structure,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int =investList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stocks = investList[position]
        holder.bind(stocks)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(companies: Companies){
            itemView.stockTextView.text = companies.companyName
            itemView.ratingTextView.text = companies.rating
        }

    }
}