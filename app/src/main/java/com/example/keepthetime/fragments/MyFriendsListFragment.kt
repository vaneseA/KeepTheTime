package com.example.keepthetime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.R
import com.example.keepthetime.adapters.MyFriendsRecyclerAdapter
import com.example.keepthetime.databinding.FragmentMyFriendsListBinding
import com.example.keepthetime.databinding.FragmentSettingsBinding
import com.example.keepthetime.models.BasicResponse
import com.example.keepthetime.models.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFriendsListFragment : BaseFragment() {

    lateinit var binding: FragmentMyFriendsListBinding

    lateinit var mFriendAdaper : MyFriendsRecyclerAdapter
    var mFriendList = ArrayList<UserData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_friends_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
    fun  getMyFriendsLsitFromServer() {
        apiList.getRequestMyFriendsList("my").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val  br = response.body()!!
                    mFriendList.clear()
                    mFriendList.addAll(br.data.friends)
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}