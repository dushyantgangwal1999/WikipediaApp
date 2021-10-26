package com.example.wikifive.Database


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Pages::class],version = 1)
abstract class PageDatabase:RoomDatabase() {

    abstract fun PageDao(): PageDao
}



//    companion object{
//        @Volatile
//        private var INSTANCE: PageDatabase?=null
////        private val LOCK = Any()
//
////        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
////            instance ?: createDatabase(context).also{ instance = it }
////        }
//
//        private fun getDatabase(context: Context):PageDatabase{
//            return INSTANCE ?: synchronized(this){
//                val instance=Room.databaseBuilder(
//                    context.applicationContext,
//                    PageDatabase::class.java,
//                    "item_database"
//                ).build()
//                INSTANCE=instance
//                instance
//
//            }
//        }
//
//
//    }
//}

