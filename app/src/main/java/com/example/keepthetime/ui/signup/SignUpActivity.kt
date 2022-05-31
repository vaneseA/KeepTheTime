package com.example.keepthetime.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}