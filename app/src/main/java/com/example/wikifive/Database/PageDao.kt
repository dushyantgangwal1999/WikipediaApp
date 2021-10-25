package com.example.wikifive.Database

import android.icu.text.CaseMap
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PageDao {

    @Query("SELECT * FROM pages WHERE title LIKE '%' || :contain1 || '%';")
    suspend fun getPages(contain1:String):List<Pages>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPages(pages: Pages)

}