package com.example.keepthetime.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.models.BasicResponse
import com.example.keepthetime.ui.main.LoginActivity
import com.example.keepthetime.ui.main.MainActivity
import com.example.keepthetime.utils.ContextUtil
import com.example.keepthetime.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashActivity : BaseActivity() {

    var isTokenOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        apiList.getRequestMyInfo(ContextUtil.getLoginToken(mContext))
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val br = response.body()!!

                        isTokenOk = true
                        GlobalData.loginUser = br.data.user
                        //서버에서 주는 응답 확인 필수
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent: Intent

            if (isTokenOk && ContextUtil.getAutoLogin(mContext)) {

                Toast.makeText(
                    mContext, "${GlobalData.loginUser!!.nick_name}님 환영합니다",
                    Toast.LENGTH_SHORT
                ).show()
                myIntent = Intent(mContext, MainActivity::class.java)
            } else {
                myIntent = Intent(mContext, LoginActivity::class.java)

            }
            startActivity(myIntent)
            finish()
        }, 2500)
    }
}