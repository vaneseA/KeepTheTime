package com.example.keepthetime.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.keepthetime.fragments.MyFriendsListFragment
import com.example.keepthetime.fragments.RequestFriendsListFragment

class FriendViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyFriendsListFragment()
            else -> RequestFriendsListFragment()
        }
    }
}