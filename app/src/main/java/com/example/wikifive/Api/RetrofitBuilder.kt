package com.example.wikithree.Api


import com.example.wikifive.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private val log= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp= OkHttpClient.Builder().addInterceptor(log)
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()
            //.build() //Doesn't require the adapter
    }

    val apiInterface:ApiInterface= getRetrofit().create(ApiInterface::class.java)
    // To Create Instance We call Above Val
}