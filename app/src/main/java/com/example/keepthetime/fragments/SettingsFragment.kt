package com.example.keepthetime.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.R
import com.example.keepthetime.databinding.FragmentSettingsBinding
import com.example.keepthetime.dialogs.CustomAlertDialog
import com.example.keepthetime.ui.main.LoginActivity
import com.example.keepthetime.utils.ContextUtil
import com.example.keepthetime.utils.GlobalData

class SettingsFragment : BaseFragment() {


    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return binding.root
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
            val alert = CustomAlertDialog(mContext, requireActivity())
            alert.myDialog()

            alert.binding.titleTxt.text = "Log Out"
            alert.binding.bodyTxt.text = "Do you want to logout?"
            alert.binding.contentEdt.visibility = View.GONE
            alert.binding.confirmBtn.setOnClickListener {
//                로그인 토큰 (from ContextUtil)만 제거하고 싶을때 (기본값으로 set하자)
//                ContextUtil.setLoginToken(mContext, "")
                ContextUtil.clear(mContext)
                GlobalData.loginUser = null
                val myIntent = Intent(mContext, LoginActivity::class.java)
                myIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


//                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK||Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(myIntent)
            }
            alert.binding.cancelBtn.setOnClickListener {
                alert.dialog.dismiss()
            }


        }


    }

    override fun setValues() {

    }
}