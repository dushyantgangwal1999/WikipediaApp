package com.example.wikithree.Api

import com.example.wikithree.model.MainDataClass
import com.google.gson.JsonArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api.php")
    suspend fun searchUser(
        @Query("action") apiaction:String,
        @Query("format") apiformat:String,
        @Query("prop") apiprop:String,
        @Query("generator") apigenerator:String,
        @Query("redirects") apiredirects:Int,
        @Query("formatversion") apiformatversion:Int,
        @Query("piprop") apipiprop:String,
        @Query("pithumbsize") apipithumbsize:Int,
        @Query("pilimit") apipilimit:Int,
        @Query("wbptterms") apiwbstterms:String,
        @Query("gpssearch") apigpssearch:String,
        @Query("gpslimit") apigpslimit:Int

    ):MainDataClass  //Returning The Object
}