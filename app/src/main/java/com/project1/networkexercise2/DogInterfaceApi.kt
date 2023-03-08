package com.project1.networkexercise2
import retrofit2.http.GET

interface ApiInterface {

    interface ApiServiceInterface {
        @GET("/api/breeds/list/all")
        suspend fun getData() : Data
    }
}