package com.pratamawijaya.coroutinesretrofit.data.repository

import com.pratamawijaya.coroutinesretrofit.data.Webservices
import com.pratamawijaya.coroutinesretrofit.data.webservices

class TodoRepository {
    private var services: Webservices = webservices

    suspend fun getTodo(id: Int) = services.getTodo(id)
}