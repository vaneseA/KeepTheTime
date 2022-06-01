package com.example.keepthetime.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    var isEmailDupOk = false
    var isNickDupOk = false
    var isPwDupOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.signUpBtn.setOnClickListener {
//    1.이메일 중복 체크
            if (!isEmailDupOk) {
                Toast.makeText(mContext, "이메일 중복 체크를 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//    2.비밀번호 중복 체크
            else if (!isPwDupOk) {
                Toast.makeText(mContext, "패스워드가 일치하지않음", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//    3. 닉네임 중복 체크
            else if (!isNickDupOk) {
                Toast.makeText(mContext, "닉네임 중복 체크를 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
//    실제 회원가입
            else {
                signUp()
            }

        }

        binding.emailDupBtn.setOnClickListener {

        }
        binding.nicDupBtn.setOnClickListener {

        }
    }

    override fun setValues() {

    }

    //    실제로 모든 조건 통과시 실행할 회원 가입 API
    fun signUp() {

    }
    fun dupCheck(type: String, value: String){
        apiList
    }
}