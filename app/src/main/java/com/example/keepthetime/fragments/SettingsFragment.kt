package com.example.keepthetime.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.R
import com.example.keepthetime.databinding.FragmentSettingsBinding
import com.example.keepthetime.ui.main.LoginActivity

class SettingsFragment : BaseFragment() {


    lateinit var binding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        프로필 이미지 변경
        binding.profileImg.setOnClickListener {

        }
//        닉네임 변경 이벤트
        binding.changeNickLayout.setOnClickListener {

        }
//        외출 준비 시간 변경 이벤트
        binding.readyTimeLayout.setOnClickListener {

        }
//        비밀번호 변경
        binding.changePwLayout.setOnClickListener {

        }
//        출발 장소 변경 이벤트
        binding.myLocationLayout.setOnClickListener {

        }
//        친구 목록 관리 이벤트
        binding.myFriendsLayout.setOnClickListener {

        }
//        로그아웃
        binding.logoutLayout.setOnClickListener {
            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)


        }


    }

    override fun setValues() {

    }
}