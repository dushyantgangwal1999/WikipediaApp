package com.example.wikithree.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.wikithree.Repository.MainRepository

import com.example.wikithree.model.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private var s1=""
    private var n1:Int=0
    private val users=MutableLiveData<List<Page>>()

    fun set(str1:String,no1:Int){
        s1=str1
        n1=no1
        viewModelScope.launch {
            searchUser(s1,n1)
        }
    }

    suspend fun searchUser(searchTerms:String,noOfItems:Int){

          withContext(Dispatchers.IO){
            val data=mainRepository.searchUser(searchTerms,noOfItems)
              Log.d("DataSize",data?.get(1)?.terms?.description?.get(0).toString())
//            Log.d("data1",data?.get(0)?.title.toString())
//            data?.get(0)?.let { it.title?.let { it1 -> Log.d("ViewModelSearchUser", it1) } }
            users.postValue(data)
        }
        //Log.d("UserData", users.value?.get(0)?.title.toString())
    }

    fun getUsers():LiveData<List<Page>>{
        Log.d("Users",users.toString())
        return users
    }

}



