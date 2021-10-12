package com.example.wikithree.Api

class ApiHelper(val apiInterface: ApiInterface) {
    // This Class Will be Called by Repository class
    suspend fun searchUser(search:String,noOfItems:Int)=apiInterface.searchUser("query",
    "json","pageimages|pageterms","prefixsearch",
    1,2,"thumbnail",
    50,10,"description",search,noOfItems)


    // We will Connect API Helper To Main Activity Using Repository
    // Here we Have Completed Network Layer SetUP

}