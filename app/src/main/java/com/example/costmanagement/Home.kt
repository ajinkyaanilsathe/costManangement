package com.example.costmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class Home : AppCompatActivity() {
    private lateinit var recyler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var list = ArrayList<BudgetDetails>()
        list.add(BudgetDetails())
        list.add(BudgetDetails())
        val gson = Gson()
        val ar_list = gson.toJson(list)
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit().putString("GetMeOut",ar_list).apply()
        recyler = findViewById(R.id.main_recyler)
        val context = this.baseContext
        val adapter = RecyclerAdapter2(context,list)
        recyler.adapter = adapter
//        var registerBtn = findViewById<Button>(R.id.registerBtn_reg)
        var investBtn = findViewById<Button>(R.id.btnInvest)



        investBtn.setOnClickListener {
            val intent = Intent(this, InvestActivity::class.java)
            startActivity(intent)

        }
}
}