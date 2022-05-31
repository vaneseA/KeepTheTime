package com.example.keepthetime.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.ui.main.LoginActivity


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({
            val myIntent: Intent
            myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)
        }, 2500)
    }
}