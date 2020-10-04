package com.example.costmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_category.*


class CategoryActivity : AppCompatActivity() {
    var categorySpinner:Spinner? = null
    lateinit var list:ArrayList<BudgetDetails>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

//        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//            .getString("GetMeOut","defaultStringIfNothingFound")
//        val gson = Gson()
//        val type = object :TypeToken<ArrayList<BudgetDetails>>(){}.type
//        val list = gson.fromJson<ArrayList<String>>(pref,type)

        list = intent.getSerializableExtra("LetMeIn") as ArrayList<BudgetDetails>

//        val cat_list = intent.extras.getSerializable("LetMeIn") as ArrayList<String>

        val cat_list = arrayListOf<String>("Housing", "General", "Electric","Water", "Misc")

        categorySpinner = findViewById(R.id.Category_spinner) as Spinner

        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            cat_list
        )
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        categorySpinner!!.adapter = arrayAdapter
        categorySpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                createAdapter()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        createAdapter()

    }

    private fun createAdapter() {
        var tList= ArrayList<BudgetDetails>()
        for (i in 2..list.size-1){
            if (categorySpinner!!.selectedItem == list.get(i).category){
                tList.add(list.get(i))
            }
        }
        val cat_recycler = recyclerView
        val adapter = CategoryHolder(tList)
        cat_recycler.adapter = adapter

    }


}