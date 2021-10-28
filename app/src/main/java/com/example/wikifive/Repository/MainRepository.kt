package com.example.wikithree.Repository



import android.app.Application
import android.content.Context
import android.util.Log
import com.example.wikifive.Database.*

import com.example.wikifive.WikiApplication

import com.example.wikithree.Api.ApiHelper
import com.example.wikithree.model.Page
import com.example.wikithree.model.Terms
import com.example.wikithree.model.Thumbnail
import kotlinx.coroutines.*

class MainRepository(/*applicationContext:Application,*/private val apiHelper: ApiHelper) {
    private lateinit var database:List<Pages>

    val context = WikiApplication.applicationContext()

    private val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context))
    var finalPage= mutableListOf<Page>()
    suspend fun searchUser(search: String, noOfItem: Int): List<Page>? {
//        withContext(Dispatchers.IO){
//            database=dbHelper.getPages(search)
//        }
        database=dbHelper.getPages(search)  //Fetching Data From DataBase


        if(database.size>=noOfItem){            //Checking Data Size
            for(i in 0 until noOfItem){
                // Data We are Fetching From Our DataBase will be of Type Pages
                finalPage.add(database[i].toPage())   //Converting Pages -> Page
            }
            return finalPage
        }

        //---------------------FETCHING DATA-----------------
        // This Will Execute When Data Size is != Number Of Items We Want.

        val result = apiHelper.searchUser(search, noOfItem)  // Making API Call Here

        val itemData=result.query?.pages
        for(i in 0 until noOfItem){
            val item=getNewEntry(itemData?.get(i)?.pageid!!.toLong(),
                itemData.get(i).title.toString(),
                itemData.get(i).terms?.description?.get(0).toString(),
                itemData.get(i).thumbnail?.source.toString()
            )

            dbHelper.insertPages(item) //Inserting Single Item:Pages Into DataBase

        }
        return result.query?.pages
    }
//--------------------------------------------
    private fun Pages.toPage()=Page(
        index = null,ns = null,pageid=pageid, Thumbnail(height = null,source = imageUrl,width = null),
        title=title, Terms(description = listOf(description))
    )
//--------------------------------------------
    private fun getNewEntry(pageID: Long, title: String, decription: String, imageUrl: String?): Pages {
        return Pages(
            pageid = pageID,
            title = title,
            description = decription,
            imageUrl = imageUrl)
    }
}
