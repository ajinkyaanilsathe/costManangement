package com.example.costmanagement


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.acitivity_new_amount.view.*


class CategoryHolder(private val list: ArrayList<BudgetDetails>) : RecyclerView.Adapter<CategoryHolder.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.acitivity_new_amount,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.amount?.text = list.get(position).amount.toString()
        holder.new_amount_category?.text = list.get(position).category.toString()
        holder.new_date?.text = list.get(position).date
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var amount:TextView? = null
        var new_date:TextView? = null
        var new_amount_category:TextView? = null

        init{
            amount = itemView.new_amount
            new_date = itemView.date_new_amount
            new_amount_category = itemView.new_amount_category
        }
    }

}

