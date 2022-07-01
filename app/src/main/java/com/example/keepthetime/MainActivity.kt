package com.example.keepthetime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.databinding.ActivityMainBinding
import com.example.keepthetime.ui.main.LoginActivity

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.logOutBtn.setOnClickListener {
            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    override fun setValues() {

    }
}