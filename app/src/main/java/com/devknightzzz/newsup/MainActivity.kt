package com.devknightzzz.newsup

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.devknightzzz.newsup.repository.SourceRepository
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        SourceRepository().getAllSources().observe(this, Observer {
            Log.i(TAG, Gson().toJson(it))
        })
    }
}
