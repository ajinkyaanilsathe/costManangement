package com.example.costmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_invest.*


class InvestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest)


        val investList : ArrayList<Companies> = ArrayList();
        investList.add(Companies("Adity Birla","8.2%"))
        investList.add(Companies("Axis Long Term Equity Fund","12.1%"))
        investList.add(Companies("ICICI Prudential Bluechip Fund","10%"))
        investList.add(Companies("DSP Tax Saver","9%"))
        investList.add(Companies("Franklin India Equity Fund","11%"))
        investList.add(Companies("ICICI Prudential  Discovery Fund","6%"))
        investList.add(Companies("Nippon India Tax Saver Fund","9.1%"))
        investList.add(Companies("DSP Equity Opportunities Fund","13.5%"))
        investList.add(Companies("Motilal Oswal Long Term Fund","11.2%"))
        investList.add(Companies("HDFC Equity Fund","8.9%"))
        investList.add(Companies("Canara Bluechip Equity Fund","5.2%"))
        investList.add(Companies("Axis Bluechip Fund","13%"))
        investList.add(Companies("UTI Equity Fund","16.1%"))
        investList.add(Companies("Mirae Asset Large Cap Fund","12.2%"))
        investList.add(Companies("Vaa Technologies","11.1%"))

        Log.i("InvestActivity",investList.toString())

        val recyclerAdapter = RecyclerAdapter(investList)
        recyclerView.adapter = recyclerAdapter
        }


}