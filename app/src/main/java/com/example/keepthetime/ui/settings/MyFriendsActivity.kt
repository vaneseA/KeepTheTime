package com.example.keepthetime.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.adapters.FriendViewPagerAdapter
import com.example.keepthetime.databinding.ActivityMyFriendsBinding
import com.google.android.material.tabs.TabLayoutMediator

class MyFriendsActivity : BaseActivity() {

    lateinit var binding: ActivityMyFriendsBinding

    lateinit var mFriendsPagerAdapter: FriendViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_friends)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mFriendsPagerAdapter = FriendViewPagerAdapter(this)
        binding.friendListViewPager.adapter = mFriendsPagerAdapter

//        ViewPager2 + TabLayout 결합 코드
//        파라미터 (tablayout의 변수, viewpager2의 변수
        TabLayoutMediator(binding.tabLayout, binding.friendListViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "My friends"
                else -> tab.text = "Add friend request"
            }
        }.attach()

    }
}