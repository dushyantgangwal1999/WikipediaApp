package com.example.wikifive.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pages")
data class Pages(
    // These Things I am Storing in my Table
    @PrimaryKey @ColumnInfo(name = "pageid") val pageid: Long,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl:String?

    )
