package com.example.hugolandimsantos.kotlingithubsearch.web

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hugo.landim.santos on 26/01/2018.
 */
interface GitHubService {

    @GET("users")
    fun search(@Query("q") query: String): Call<GitHubResponse>
}