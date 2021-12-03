package com.example.retrofitpractice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitpractice.models.GithubRepo
import com.example.retrofitpractice.network.GithubClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val TAG = "/ ${this::class.simpleName}"
    val githubRepos = MutableLiveData<List<GithubRepo>>()
    var visible = MutableLiveData<Boolean>(true)
    var text = MutableLiveData<String>("")

    fun getGithubRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            GithubClient().client?.getReposResponse("gunhee-woo")?.let { response ->
                if(response.isSuccessful) {
                    val body = response.body()
                    Log.d(TAG, App.Function() + ", ${body.toString()}")
                    body?.let {
                        githubRepos.postValue(it)
                    }
                }
            }
        }
    }

    fun getGithubRepos2() {
        GithubClient().client?.getReposCall("gunhee-woo")?.enqueue(object: Callback<List<GithubRepo>> {
            override fun onResponse(
                call: Call<List<GithubRepo>>,
                response: Response<List<GithubRepo>>
            ) {
            }

            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
            }

        })
    }

    fun getGithubRepos3() {
        CoroutineScope(Dispatchers.IO).launch {
            GithubClient().client?.getReposSuspend("gunhee-woo")?.let {
                githubRepos.postValue(it)
            }
        }
    }

    fun getGithubRepos4() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = withContext(Dispatchers.IO) {
                GithubClient().client?.getReposSuspend("gunhee-woo")
            }
            githubRepos.postValue(result)
        }

    }

    override fun onCleared() {
        super.onCleared()
    }
}

//class MainViewModelFactory(private val repository: KmoocRepository) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            return MainViewModel(repository) as T
//        }
//        throw IllegalAccessException("Unkown Viewmodel Class")
//    }
//}