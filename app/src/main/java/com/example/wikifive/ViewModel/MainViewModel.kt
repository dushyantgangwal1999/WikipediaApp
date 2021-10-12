package com.example.wikithree.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikifive.SecondActivity
import com.example.wikithree.Repository.MainRepository

import com.example.wikithree.model.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val users=MutableLiveData<List<Page>>()
    suspend fun searchUser(searchTerms:String,noOfItems:Int){

          withContext(Dispatchers.IO){
            val data=mainRepository.searchUser(searchTerms,noOfItems)
            Log.d("data",data.toString())
            //data?.get(0)?.let { it.title?.let { it1 -> Log.d("ViewModelSearchUser", it1) } }
            users.postValue(data)
        }
    }

    fun getUsers():LiveData<List<Page>>{
        Log.d("USers",users.toString())
        return users
    }

}



