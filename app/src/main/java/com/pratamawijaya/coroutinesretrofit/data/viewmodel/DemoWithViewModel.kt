package com.pratamawijaya.coroutinesretrofit.data.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pratamawijaya.coroutinesretrofit.R
import com.pratamawijaya.coroutinesretrofit.data.model.Todo
import kotlinx.android.synthetic.main.activity_main.*

class DemoWithViewModel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProvider(this).get(DemoViewModel::class.java)

        vm._todo.observe(this, Observer { todo ->
            showResult(todo)
        })

        btnReq.setOnClickListener {
            val rnd = (1..66).random()
            vm.getTodo(rnd)
        }

        btnViewmodel.visibility = GONE
    }

    private fun showResult(result: Todo) {
        Log.d("debug", "tag result $result")
        Toast.makeText(this, "result ${result.title}", Toast.LENGTH_SHORT).show()
    }
}