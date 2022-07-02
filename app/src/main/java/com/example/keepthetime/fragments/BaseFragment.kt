package com.example.keepthetime.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.keepthetime.api.APIList
import com.example.keepthetime.api.ServerApi
import retrofit2.Retrofit
import retrofit2.create

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var retrofit: Retrofit
    lateinit var apiList: APIList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireContext()
        retrofit = ServerApi.getRetrofit()
        apiList = retrofit.create(APIList::class.java)
    }

    abstract  fun setupEvents()
    abstract  fun setValues()
}