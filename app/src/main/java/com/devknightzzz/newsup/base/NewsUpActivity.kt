package com.devknightzzz.newsup.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * @author vinayagasundar
 */
abstract class NewsUpActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int
}