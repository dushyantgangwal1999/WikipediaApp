package com.example.wikifive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchEt=findViewById<EditText>(R.id.searchField)
        val noOfItemsEt=findViewById<EditText>(R.id.NoOfItems)
        val searchButtonEt=findViewById<Button>(R.id.search_button)
        searchButtonEt.setOnClickListener {
            val searchTerm=searchEt.text.toString()
            val noOfItems=noOfItemsEt.text.toString()
            if (searchTerm.isEmpty()){
                searchEt.error = "This is Required!"
            }else{
                if (noOfItems.isEmpty()){
                    noOfItemsEt.error="This is Required !"
                }else{
                    val intent= Intent(this,SecondActivity::class.java)
                    intent.putExtra("SearchTerm", searchTerm)
                    intent.putExtra("noOfItems",noOfItems)
                    startActivity(intent)
                }
            }

        }
    }
}