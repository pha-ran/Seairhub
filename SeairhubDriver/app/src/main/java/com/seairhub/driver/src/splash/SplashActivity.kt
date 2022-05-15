package com.seairhub.driver.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.seairhub.driver.config.BaseActivity
import com.seairhub.driver.databinding.ActivitySplashBinding
import com.seairhub.driver.src.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)
        Handler(Looper.getMainLooper()).postDelayed({
            dismissLoadingDialog()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}