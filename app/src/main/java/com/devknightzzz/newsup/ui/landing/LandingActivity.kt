package com.devknightzzz.newsup.ui.landing

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import com.devknightzzz.newsup.NewsAndroidAPI
import com.devknightzzz.newsup.R
import com.devknightzzz.newsup.Status
import com.devknightzzz.newsup.base.NewsUpActivity
import com.google.gson.Gson

class LandingActivity : NewsUpActivity() {

    companion object {
        val TAG = "LandingActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NewsAndroidAPI.instance.getArticleDataSource()
                .getArticles("the-next-web")
                .observe(this, Observer {
            it?.let {
                if (it.status == Status.SUCCESS) {
                    Log.i(TAG, Gson().toJson(it.data))
                }
            }
        })
    }

    override fun getLayoutResId(): Int = R.layout.activity_main
}
