package com.example.wikifive.Database
//
//
import android.content.Context
import androidx.room.Room


object DatabaseBuilder{
    private var INSTANCE:PageDatabase?=null


    fun getInstance(context: Context?):PageDatabase{

        if(INSTANCE==null){
            synchronized(PageDatabase::class){
                //INSTANCE=buildRoomDB(context.applicationContext)
                INSTANCE=buildRoomDB(context)
            }

        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context?)= context?.let {
        Room.databaseBuilder(
        //context.applicationContext,PageDatabase::class.java,"Wikipedia-APP"
            it,PageDatabase::class.java,"Wikipedia-APP"
    ).build()
    }

//    fun insertPage(context: Context){
//        val dbInstance=getInstance(context)
//        //dbInstance.PageDao().insertPa
//    }

}