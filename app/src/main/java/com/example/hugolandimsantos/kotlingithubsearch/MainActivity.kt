package com.example.hugolandimsantos.kotlingithubsearch

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import com.example.hugolandimsantos.kotlingithubsearch.web.GitHubApi
import com.example.hugolandimsantos.kotlingithubsearch.web.GitHubCallback
import com.example.hugolandimsantos.kotlingithubsearch.web.GitHubUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var listResponse: List<GitHubUser>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list_result.setOnItemClickListener { parent, view, position, id ->


            val user = listResponse?.get(position)
            if (user != null) {
                Toast.makeText(this, user.avatar_url, Toast.LENGTH_SHORT).show()
            }
        }

        btn_search.setOnClickListener {

            closeKeyboard()

            val text = edt_query.text.toString()

            if (!text.isEmpty()) {

                val callback = object : GitHubCallback {
                    override fun onGotUsers(users: List<GitHubUser>?) {

                        listResponse = users

                        val loginList = users?.map { it.login }

                        Log.i(TAG(), loginList.toString())

                        val adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, loginList)

                        list_result.adapter = adapter as ListAdapter?

                    }

                    override fun onGotError(error: String) {
                        Log.e(TAG(), error)
                        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    }

                }

                GitHubApi().search(text, callback)

            } else {
                Toast.makeText(this, "Digite algo!!!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun closeKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken,
                HIDE_NOT_ALWAYS)
    }

    private fun TAG() = MainActivity::class.java.simpleName
}
