package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitpractice.network.GithubClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.http.Body
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private val TAG = "" + this::class.java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        clickBtn.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                KmoocRepository().getRepository()?.let { response ->
//                    if(response.isSuccessful) {
//                        val body = response.body()
//                        body?.let {
//                            Log.d(TAG, "body : ${body.toString()}")
//                        }
//                    }
//                }
//            }
//        }

        clickBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
//                GithubClient().client?.getReposResponse("gunhee-woo")?.let { response ->
//                    if(response.isSuccessful) {
//                        val body = response.body()
//                        body?.let {
//                            Log.d(TAG, "body : ${body.toString()}")
//                        }
//                    }
//                }
//                GithubClient().client?.getReposCall("gunhee-woo")?.enqueue()
            }
        }

//        clickBtn.setOnClickListener {
//            GithubClient().client?.getReposSingle("gunhee-woo")
//                ?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribe({ items ->
//                    items.forEach { Log.d(TAG, "$it") }
//                }, { e ->
//                    Log.d(TAG, "$e")
//                })
//        }
    }
}