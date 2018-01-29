package com.example.hugolandimsantos.kotlingithubsearch.web

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by hugo.landim.santos on 26/01/2018.
 */
class GitHubApi {

    private var service: GitHubService

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(GitHubService::class.java)
    }

    fun search(query: String, callbackUi: GitHubCallback) {

        val response = service.search(query)

        val callback = object : Callback<GitHubResponse> {

            override fun onFailure(call: Call<GitHubResponse>?, t: Throwable?) {
                callbackUi.onGotError(t?.message.toString())
            }

            override fun onResponse(call: Call<GitHubResponse>?, response: Response<GitHubResponse>?) {
                callbackUi.onGotUsers(response?.body()?.items)
            }

        }
        response.enqueue(callback)
    }
}