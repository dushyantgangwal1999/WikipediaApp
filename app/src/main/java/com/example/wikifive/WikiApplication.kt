package com.example.wikifive

import android.app.Application
import android.content.Context

class WikiApplication: Application() {
//    init {
//        instance = this
//    }

    companion object {
        private lateinit var instance: WikiApplication

        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any
        instance = this
        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context? = WikiApplication.applicationContext()
    }
}
