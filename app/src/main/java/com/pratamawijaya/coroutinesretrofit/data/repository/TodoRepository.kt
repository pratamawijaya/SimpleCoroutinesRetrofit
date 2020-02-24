package com.pratamawijaya.coroutinesretrofit.data.repository

import com.pratamawijaya.coroutinesretrofit.data.Webservices
import com.pratamawijaya.coroutinesretrofit.data.myApi

class TodoRepository {
    private var services: Webservices = myApi

    suspend fun getTodo(id: Int) = services.getTodo(id)
}