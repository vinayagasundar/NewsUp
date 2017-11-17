package com.devknightzzz.newsup.ui.onboarding

import android.os.Bundle
import com.devknightzzz.newsup.R
import com.devknightzzz.newsup.base.NewsUpActivity
import com.devknightzzz.newsup.ui.sources.selection.SourceSelectionFragment

/**
 * @author vinayagasundar
 */
class OnBoardingActivity: NewsUpActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = SourceSelectionFragment()
            supportFragmentManager?.beginTransaction()?.add(R.id.container, fragment)?.commit()
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_onboarding
}
