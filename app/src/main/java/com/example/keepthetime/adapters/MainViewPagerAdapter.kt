package com.example.keepthetime.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.keepthetime.fragments.InvitedAppointmentsFragment
import com.example.keepthetime.fragments.MyAppointmentsFragment
import com.example.keepthetime.fragments.SettingsFragment

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyAppointmentsFragment()
            1 -> InvitedAppointmentsFragment()
            else -> SettingsFragment()

        }
    }
}