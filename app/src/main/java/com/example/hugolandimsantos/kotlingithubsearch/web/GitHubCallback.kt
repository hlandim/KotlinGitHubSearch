package com.example.hugolandimsantos.kotlingithubsearch.web

/**
 * Created by hugo.landim.santos on 26/01/2018.
 */
interface GitHubCallback {
    fun onGotUsers(users: List<GitHubUser>?)
    fun onGotError(error: String)
}