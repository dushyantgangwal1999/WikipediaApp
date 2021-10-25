package com.example.wikifive.Database

interface DatabaseHelper {
    suspend fun getPages(contain:String):List<Pages>

    suspend fun insertPages(pages: Pages)

}

