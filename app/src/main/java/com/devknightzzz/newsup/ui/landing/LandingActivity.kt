package com.devknightzzz.newsup.ui.landing

import android.os.Bundle
import com.devknightzzz.newsup.R
import com.devknightzzz.newsup.base.NewsUpActivity

class LandingActivity : NewsUpActivity() {

    companion object {
        val TAG = "LandingActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main
}
