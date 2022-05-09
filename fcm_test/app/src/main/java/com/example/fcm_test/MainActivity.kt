package com.example.fcm_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().token.addOnCompleteListener (OnCompleteListener {
            if (!it.isSuccessful) {
                println("Fetching FCM registration token failed : ${it.exception}")
                return@OnCompleteListener
            }

            val token = it.result

            // 토큰 처리
        })
    }
}