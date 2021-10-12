package com.example.wikithree.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wikithree.Api.ApiHelper
import com.example.wikithree.Repository.MainRepository

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(MainRepository(apiHelper))as T
    }


}