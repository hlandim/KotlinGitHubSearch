package com.example.hugolandimsantos.kotlingithubsearch.web

/**
 * Created by hugo.landim.santos on 26/01/2018.
 */

data class GitHubResponse(val total_count: Int, val items: List<GitHubUser>?)

data class GitHubUser(val login: String,
                      val avatar_url: String)

