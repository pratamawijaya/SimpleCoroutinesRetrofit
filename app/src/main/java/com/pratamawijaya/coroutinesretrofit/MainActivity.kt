package com.pratamawijaya.coroutinesretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.pratamawijaya.coroutinesretrofit.data.model.Todo
import com.pratamawijaya.coroutinesretrofit.data.repository.TodoRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = TodoRepository()

        btnReq.setOnClickListener {
            lifecycleScope.launch {
                val result = withContext(Dispatchers.IO) { repository.getTodo(10) }
                showResult(result)
            }
        }
    }

    private fun showResult(result: Todo) {
        Log.d("debug","tag result ${result.toString()}")
        Toast.makeText(this, "result ${result.title}", Toast.LENGTH_SHORT).show()
    }

}
