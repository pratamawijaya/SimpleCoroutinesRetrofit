package com.pratamawijaya.coroutinesretrofit.data

import com.pratamawijaya.coroutinesretrofit.data.model.Todo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservices {
    @GET("/todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Todo
}

val webservices: Webservices by lazy {
    Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Webservices::class.java)
}