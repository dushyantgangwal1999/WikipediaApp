package com.example.wikifive.Database

class DatabaseHelperImpl(private val pageDatabase: PageDatabase):DatabaseHelper {
    override suspend fun getPages(contain:String): List<Pages>
    =pageDatabase.PageDao().getPages(contain)



    override suspend fun insertPages(pages: Pages)
    = pageDatabase.PageDao().insertPages(pages)


}