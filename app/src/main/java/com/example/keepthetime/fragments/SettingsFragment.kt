package com.example.keepthetime.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.keepthetime.R
import com.example.keepthetime.databinding.FragmentSettingsBinding
import com.example.keepthetime.dialogs.CustomAlertDialog
import com.example.keepthetime.models.BasicResponse
import com.example.keepthetime.ui.main.LoginActivity
import com.example.keepthetime.utils.ContextUtil
import com.example.keepthetime.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
//            갤러리를 개발자가 이용 : 유저에게 허락을 받아야한다 => 권한 세팅
//            TedPermission 라이브러리

//            권한이 OK 일때
//            갤러리로 사진을 가지러 이동(추가작업) => Intent (3) / (4) 결합


        }
//        닉네임 변경 이벤트
        binding.changeNickLayout.setOnClickListener {
            val alert = CustomAlertDialog(mContext, requireActivity())
            alert.myDialog()
            alert.binding.titleTxt.text = "Change Nickname"
            alert.binding.bodyTxt.visibility = View.GONE
            alert.binding.contentEdt.hint = "Insert Nickname for changing"
            alert.binding.contentEdt.inputType = InputType.TYPE_CLASS_TEXT

            alert.binding.confirmBtn.setOnClickListener {
                apiList.patchRequestEditUserInfo(
                    "nickname",
                    alert.binding.contentEdt.text.toString()
                ).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!

                            GlobalData.loginUser = br.data.user

//                            binding.nickNameTxt.text = br.data.user.nickname
                            setUserData()

                            alert.dialog.dismiss()
                        }
//                        중복된 닉네임과 같은 문제가 발생
                        else {
                            val errorBodyStr = response.errorBody()!!.string()
                            val jsonObj = JSONObject(errorBodyStr)
                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
            alert.binding.cancelBtn.setOnClickListener {
                alert.dialog.dismiss()
            }

//            apiList.patchRequestEditUserInfo()
        }
//        외출 준비 시간 변경 이벤트
        binding.readyTimeLayout.setOnClickListener {
            val alert = CustomAlertDialog(mContext, requireActivity())
            alert.myDialog()
            alert.binding.titleTxt.text = "Set time up for ready"
            alert.binding.bodyTxt.visibility = View.GONE
            alert.binding.contentEdt.hint = "How many time do you take for ready?"
            alert.binding.contentEdt.inputType = InputType.TYPE_CLASS_NUMBER

            alert.binding.confirmBtn.setOnClickListener {
                apiList.patchRequestEditUserInfo(
                    "ready_minute",
                    alert.binding.contentEdt.text.toString()
                ).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!

                            GlobalData.loginUser = br.data.user

//                            binding.nickNameTxt.text = br.data.user.nickname
                            setUserData()

                            alert.dialog.dismiss()
                        }
//                        중복된 닉네임과 같은 문제가 발생
                        else {
                            val errorBodyStr = response.errorBody()!!.string()
                            val jsonObj = JSONObject(errorBodyStr)
                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
            alert.binding.cancelBtn.setOnClickListener {
                alert.dialog.dismiss()
            }
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
        setUserData()

        when (GlobalData.loginUser!!.provider) {
            "kakao" -> {}
            "facebook" -> {}
            else -> binding.socialLoginImg.visibility = View.GONE
        }
    }

    fun setUserData() {
        Glide.with(mContext)
            .load(GlobalData.loginUser!!.profileImg)
            .into(binding.profileImg)

        binding.nickNameTxt.text = GlobalData.loginUser!!.nickname

//        [연습문제] if 준비시간이 1시간이 넘을 경우 -> x시간 x분으로 나타내보자.
        binding.readyTimeTxt.text = "${GlobalData.loginUser!!.readyMinute} minute"

    }
}