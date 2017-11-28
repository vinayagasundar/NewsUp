package com.devknightzzz.newsup.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.devknightzzz.newsup.ISettingDataSource
import com.devknightzzz.newsup.ui.landing.LandingActivity
import com.devknightzzz.newsup.NewsAndroidAPI
import com.devknightzzz.newsup.ui.onboarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingDataSource: ISettingDataSource = NewsAndroidAPI.instance.getSettingDataSource()

        val redirectScreen = if (settingDataSource.isFirstLaunch()) {
            settingDataSource.setFirstLaunch(false)
            OnBoardingActivity::class.java
        } else {
            LandingActivity::class.java
        }

        Handler().postDelayed({
            startActivity(Intent(this, redirectScreen))
        }, 3000)
    }
}
