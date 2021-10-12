package com.example.wikithree.Repository

import android.util.Log
import com.example.wikithree.Api.ApiHelper
import com.example.wikithree.model.Page

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun searchUser(search: String, noOfItem: Int): List<Page>? {
        val result = apiHelper.searchUser(search, noOfItem)
        Log.d("Repo",result.toString())
        return result.query?.pages
    }
}