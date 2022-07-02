package com.example.keepthetime.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.adapters.MainViewPagerAdapter
import com.example.keepthetime.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var mPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        binding.logOutBtn.setOnClickListener {
//            val myIntent = Intent(mContext, LoginActivity::class.java)
//            startActivity(myIntent)
//            finish()
//        }
    }

    override fun setValues() {
        mPagerAdapter = MainViewPagerAdapter(this)
        binding.mainViewPager.adapter = mPagerAdapter

        binding.mainViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.myAppointments -> binding.mainViewPager.currentItem = 0
                R.id.invitedAppointments -> binding.mainViewPager.currentItem = 1
                R.id.settings -> binding.mainViewPager.currentItem = 2
            }
            return@setOnItemSelectedListener true
        }
    }
}