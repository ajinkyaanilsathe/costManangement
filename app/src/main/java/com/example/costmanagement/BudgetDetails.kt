package com.example.costmanagement

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
class BudgetDetails(val amount:String, val date: String, val category:String) : Parcelable {
    constructor() : this("15000","12/07/2020","")


//    fun setAmount( amount2: String): String{
//        Log.i("BugdetDetails","HELLO ERROR OCCURED")
//        return (this.amount.toInt()-amount2.toInt()).toString()
//    }
    
}
