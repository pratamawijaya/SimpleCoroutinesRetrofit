package com.pratamawijaya.coroutinesretrofit.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratamawijaya.coroutinesretrofit.data.model.Todo
import com.pratamawijaya.coroutinesretrofit.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DemoViewModel : ViewModel() {

    private var repo = TodoRepository()

    val _todo = MutableLiveData<Todo>()
    val _error = MutableLiveData<String>()

    fun getTodo(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = repo.getTodo(id)
                    _todo.postValue(result)
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            _error.postValue("Network Error")
                        }
                        is HttpException -> {
                            val code = throwable.code()
                            val errorResponse = throwable.message()
                            _error.postValue("Error $code $errorResponse")
                        }
                        else -> {
                            _error.postValue("Unknown Error")
                        }
                    }
                }
            }
        }
    }
}