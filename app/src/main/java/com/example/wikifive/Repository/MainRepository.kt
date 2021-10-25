package com.example.wikithree.Repository

import android.app.Application

import android.util.Log
import com.example.wikifive.Database.*




import com.example.wikithree.Api.ApiHelper
import com.example.wikithree.model.Page
import com.example.wikithree.model.Terms
import com.example.wikithree.model.Thumbnail
import kotlinx.coroutines.*

class MainRepository(applicationContext:Application,private val apiHelper: ApiHelper) {
    //val db:PageDatabase

    private lateinit var database:List<Pages>
    private val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
    var finalPage= mutableListOf<Page>()
    suspend fun searchUser(search: String, noOfItem: Int): List<Page>? {

        withContext(Dispatchers.IO){
            database=dbHelper.getPages(search)
        }
        Log.d("Repo1","Search USer")

        if(database.size>=noOfItem){

            for(i in 0 until noOfItem){
                val con=database.get(i).toPage()
                finalPage.add(con)
            }
            Log.d("Above","Hello")
            return finalPage


        }
        //-----------------------------FETCHING DATA--------------------------------
        val result = apiHelper.searchUser(search, noOfItem)
//                Log.d("Repo",result.toString())
        Log.d("Repo11",result.query?.pages?.get(0).toString())
//                Log.d("ouput12",result.query?.pages?.size.toString())

        val itemData=result.query?.pages

        for(i in 0 until noOfItem){
            val item=getNewEntry(itemData?.get(i)?.pageid!!.toLong(),
                itemData.get(i).title.toString(),
                itemData.get(i).terms?.description?.get(0).toString(),
                itemData.get(i).thumbnail?.source.toString()
            )

            Log.d("ICount",i.toString())
            withContext(Dispatchers.Unconfined){dbHelper.insertPages(item)}
            Log.d("ItemLog",item.toString())



        }

        return result.query?.pages
//            Log.d("FetchData",database.toString())
//            Log.d("FetchData",database.size.toString())

    }

    private fun Pages.toPage()=Page(
        index = null,ns = null,pageid=pageid, Thumbnail(height = null,source = imageUrl,width = null),
        title=title, Terms(description = listOf(description))
    )

    private fun getNewEntry(pageID: Long, title: String, decription: String, imageUrl: String?): Pages {
        return Pages(
            pageid = pageID,
            title = title,
            description = decription,
            imageUrl = imageUrl)
    }


}
