package com.example.costmanagement


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Layout
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.acitivity_add_amount.view.*
import kotlinx.android.synthetic.main.acitivity_amount.view.*
import kotlinx.android.synthetic.main.acitivity_new_amount.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RecyclerAdapter2(val context: Context, var list: ArrayList<BudgetDetails>) : RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>(){
    var getCategory:String? = null
    var list2:ArrayList<String>?= null
    val KEY:String = "KEY"
    private val TAG: String = RecyclerAdapter2::class.java.getSimpleName()
    init{
        val gson = Gson()
        val pref = PreferenceManager.getDefaultSharedPreferences(context).getString("GetMeOut","")
        val type = object : TypeToken<ArrayList<BudgetDetails>>(){}.type
        list = gson.fromJson<ArrayList<BudgetDetails>>(pref,type)
        for (i in 0..list.size-1){
            d("hehe",list.get(i).category.toString())
        }

    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when(viewType){
            0 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acitivity_amount,parent,false)
                return ViewHolder(view,list.get(viewType))
            }
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acitivity_add_amount,parent,false)
                return ViewHolder(view,list.get(viewType))
            }
            else ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acitivity_new_amount,parent,false)
                return ViewHolder(view,list.get(viewType))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.amount?.text = list.get(position).amount.toString()
        holder.new_amount_category?.text = list.get(position).category.toString()
        holder.new_date?.text = list.get(position).date
        //holder.totalAmount?.text = list.get(position).amount
        val currentDateTime = LocalDateTime.now()
        val dateTime = currentDateTime.format(DateTimeFormatter.ISO_DATE)

        if (position == 1){
            holder.add?.setOnClickListener {
                addItem(position,
                    holder.add_amount?.text.toString(),

                    dateTime.toString(),
                    holder.cat_view?.selectedItem.toString()
                )
                Log.d(TAG, "HOLDER:error")

            }



            val list1 = arrayOf("Housing", "General", "Electric","Water", "Misc")
            val arrayAdapter = ArrayAdapter(
                context,
                R.layout.support_simple_spinner_dropdown_item,
                list1
            )
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            holder.cat_view?.adapter = arrayAdapter
            holder.cat_view?.onItemSelectedListener


        }
        holder.new_amount_category?.setOnClickListener {
            list2 = arrayListOf<String>("Housing", "General", "Electric","Water", "Misc")
            val intent = Intent(context,CategoryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("LetMeIn", list)
            context.startActivity(intent)

        }


    }



    fun addItem(position: Int,p0:String,p1:String,p2:String){
        list.add(BudgetDetails(p0,p1,p2))
        notifyItemInserted(list.size)
        //open fun set(index: Int, element: E): E
//        Log.i("RecyclerAdapater2","HELLO ERROR OCCURED HERE")
//        list.get(0).setAmount(p1)
//        Log.i("RecyclerAdapater2","HELLO ERROR OCCURED AFTER")
//        notifyItemChanged(0)
        val gson = Gson()
        val ar_list = gson.toJson(list)
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit().putString("GetMeOut",ar_list).apply()



    }

    inner class ViewHolder(itemView: View,val details: BudgetDetails) : RecyclerView.ViewHolder(itemView){
        var amount:TextView? = null
        var new_date:TextView? = null
        var new_amount_category:TextView? = null
        var add:Button? = null
        var add_amount:TextView? = null
        var categorybtn:Button? = null
        var cat_layout:LinearLayout? = null
        var cat_view:Spinner? = null
        //var totalAmount:TextView?= null
        init{
            new_date = itemView.date_new_amount
            new_amount_category = itemView.new_amount_category
            add = itemView.add_button
            add_amount = itemView.input_amount
            amount = itemView.new_amount
//            categorybtn = itemView.add_button
            cat_layout = itemView.category_drop
            cat_view = itemView.select_category
          //  totalAmount = itemView.total_amount
        }
    }


}

