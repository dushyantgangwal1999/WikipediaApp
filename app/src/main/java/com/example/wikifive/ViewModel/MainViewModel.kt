package com.example.wikithree.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.wikifive.Database.Pages
import com.example.wikithree.Repository.MainRepository

import com.example.wikithree.model.Page
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val users=MutableLiveData<List<Page>>()
    private val user=MutableLiveData<List<Pages>>()

    suspend fun searchUser(searchTerms:String,noOfItems:Int){
/*
        withContext(Dispatchers.Main){
            val data1= mainRepository.getItems(searchTerms)
            Log.d("getItems",data1.toString())
            user.postValue(data1)
        }
 */
        //       if(user.equals(null)){

        withContext(Dispatchers.IO){
            val data=mainRepository.searchUser(searchTerms,noOfItems)
            Log.d("data1",data.toString())
            //data?.get(0)?.let { it.title?.let { it1 -> Log.d("ViewModelSearchUser", it1) } }
            users.postValue(data)
//            }
/*
            withContext(Dispatchers.IO){
                for(i in 0..noOfItems){
                    mainRepository.insertData(
                        users.value?.get(i)?.pageid!!.toLong(),
                        users.value?.get(i)?.title.toString(),users.value?.get(i)?.terms?.description?.get(0).toString(),
                        users.value?.get(i)?.thumbnail?.source.toString())


                }}
*/
        }


    }
    //-----------------------------
    /*
    fun getUser():LiveData<List<Pages>>{
        Log.d("User",user.value?.get(0).toString())
        return user
    }
    */
    //-------------------------

    fun getUsers():LiveData<List<Page>>{
        Log.d("Users",users.toString())
        return users
    }

}




