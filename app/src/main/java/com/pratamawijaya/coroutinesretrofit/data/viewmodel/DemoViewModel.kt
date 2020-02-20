package com.pratamawijaya.coroutinesretrofit.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratamawijaya.coroutinesretrofit.data.model.Todo
import com.pratamawijaya.coroutinesretrofit.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel : ViewModel() {

    private var repo = TodoRepository()

    val _todo = MutableLiveData<Todo>()

    fun getTodo(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { repo.getTodo(id) }
            _todo.postValue(result)
        }
    }
}