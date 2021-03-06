package com.pratamawijaya.coroutinesretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.pratamawijaya.coroutinesretrofit.data.model.Todo
import com.pratamawijaya.coroutinesretrofit.data.repository.TodoRepository
import com.pratamawijaya.coroutinesretrofit.data.viewmodel.DemoWithViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = TodoRepository()

        btnReq.setOnClickListener {
            lifecycleScope.launch {
                val rnd = (1..66).random()
                val result = withContext(Dispatchers.IO) { repository.getTodo(rnd) }
                showResult(result)
            }
        }

        btnViewmodel.setOnClickListener {
            startActivity(Intent(this, DemoWithViewModel::class.java))
        }

    }

    /**
     * for testing purpose
     */
    private fun testRunCoroutines() {
        // test launch 3 coroutines

        lifecycleScope.launch {
            for (i in 1..100) {
                println("I'm working in thread ${Thread.currentThread().name}")
                postItem(Item("data $i"))
            }
        }

        val threads = 4
        val myCustomDispatcher = Executors.newFixedThreadPool(threads).asCoroutineDispatcher()

        lifecycleScope.launch(myCustomDispatcher) {
            for (i in 1..100) {
                println("I'm working in thread ${Thread.currentThread().name}")
                postItem(Item("data $i"))
            }
        }

        lifecycleScope.launch(myCustomDispatcher) {
            for (i in 1..100) {
                println("I'm working in thread ${Thread.currentThread().name}")
                postItem(Item("data $i"))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        job.cancel()
//        Log.d("debug", "destroy job ${job.isCancelled}")
    }

    private fun showResult(result: Todo) {
        Log.d("debug", "tag result $result")
        Toast.makeText(this, "result $result", Toast.LENGTH_SHORT).show()
    }

    private suspend fun postItem(item: Item) {
        val token = requestToken()
        val post = createPost(token, item)
        processPost(post)
    }

    private suspend fun requestToken(): Token {
        val rnds = (0..50).random()
        delay(500L)
        return Token("mytoken_$rnds")
    }

    private suspend fun createPost(token: Token, item: Item): Post {
        val rnds = (10..5000).random()
        delay(500L)
        return Post("token : ${token.token} item ${item.data} $rnds")
    }

    private fun processPost(post: Post) {
        Log.d("tag", "debug post ${post.title}")
    }
}

data class Item(val data: String)

data class Post(val title: String)

data class Token(val token: String)
