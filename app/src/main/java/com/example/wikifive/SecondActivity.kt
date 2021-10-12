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
    private lateinit var search :String
    private lateinit var noOfItem : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent=intent
        search = intent.getStringExtra("SearchTerm").toString()
        noOfItem = intent.getStringExtra("noOfItems").toString()
        mainViewModel=
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiInterface))).get(MainViewModel::class.java)
        setUI()
        setupObserver()
        GlobalScope.launch {
            try {
                mainViewModel.searchUser(search, noOfItem.toInt())
            }catch (e:Exception){
                Log.d("Exception",e.toString())
            }

        }

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