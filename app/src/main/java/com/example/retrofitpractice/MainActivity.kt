package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "" + this::class.java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                KmoocRepository().getRepository()?.let { response ->
                    if(response.isSuccessful) {
                        val body = response.body()
                        body?.let {
                            Log.d(TAG, "body : ${body.toString()}")
                        }
                    }
                }
            }
        }
    }
}