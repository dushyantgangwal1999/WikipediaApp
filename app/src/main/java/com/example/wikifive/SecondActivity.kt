package com.example.wikifive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wikithree.Api.ApiHelper
import com.example.wikithree.Api.RetrofitBuilder
import com.example.wikithree.ViewModel.MainViewModel
import com.example.wikithree.ViewModel.ViewModelFactory
import com.example.wikithree.adapter.MainAdapter
import com.example.wikithree.model.Page
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private var search :String=""
    private lateinit var noOfItem : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent=intent
        search = intent.getStringExtra("SearchTerm").toString()
        noOfItem = intent.getStringExtra("noOfItems").toString()

        Log.d("Arg",search+noOfItem)
//        mainViewModel.searchTerm=search
//        mainViewModel.noOfItems=noOfItem
        mainViewModel=
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiInterface))).get(MainViewModel::class.java)
        setUI()
        setupObserver()
        Log.d("MainActivity", mainViewModel.getUsers().toString())
        GlobalScope.launch {
            try {
                mainViewModel.searchUser(search, noOfItem.toInt())
            }catch (e:Exception){
//                Toast.makeText(this,"You Have Entered" +
//                        "Nothing",Toast.LENGTH_LONG).show()
                Log.d("Exception",e.toString())
            }

        }

//        val result=findViewById<TextView>(R.id.searchview)
//        val result2=findViewById<TextView>(R.id.noView)
//        result.text=search
//        result2.text=noOfItems



    }

    fun getSearchTerm(): String {
        return search
    }
    fun getNoOfItems(): String {
        return noOfItem
    }
    private fun setupObserver() {

        mainViewModel.getUsers().observe(this, Observer {
            //Log.d("Observe",it[0].toString())
            it?.let {
                renderList(it)
            }
        })
    }

    private fun renderList(it: List<Page>) {

        adapter.addPages(it)
        adapter.notifyDataSetChanged()

    }

    private fun setUI() {



        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter= MainAdapter(arrayListOf()) //Initializing
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter=adapter



    }
}