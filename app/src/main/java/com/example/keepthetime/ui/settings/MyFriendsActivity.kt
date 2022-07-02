package com.example.keepthetime.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.databinding.ActivityMyFriendsBinding

class MyFriendsActivity : BaseActivity() {

    lateinit var binding : ActivityMyFriendsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_friends)
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}