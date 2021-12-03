package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpractice.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "" + this::class.java

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
        viewModel.githubRepos.observe(this) {
            adapter.updateGithubRepos(it)
        }
        viewModel.visible.observe(this) {
            if(it) visibleTv.text = "visible" else "gone"
        }
        binding.rcv.adapter = adapter
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
        clickBtn.setOnClickListener {
            viewModel.getGithubRepos4()
            viewModel.visible.value = viewModel.visible.value?.not()

        }

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

//        clickBtn.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                GithubClient().client?.getReposResponse("gunhee-woo")?.let { response ->
//                    if(response.isSuccessful) {
//                        val body = response.body()
//                        body?.let {
//                            Log.d(TAG, "body : ${body.toString()}")
//                        }
//                    }
//                }
//                GithubClient().client?.getReposCall("gunhee-woo")?.enqueue()
//            }
//        }

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