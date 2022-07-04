package com.example.keepthetime.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime.BaseActivity
import com.example.keepthetime.R
import com.example.keepthetime.adapters.PlaceRecyclerAdapter
import com.example.keepthetime.databinding.ActivityMyPlaceListBinding
import com.example.keepthetime.models.BasicResponse
import com.example.keepthetime.models.PlaceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPlaceListActivity : BaseActivity() {

    lateinit var binding : ActivityMyPlaceListBinding

    lateinit var mPlaceRecycleAdapter : PlaceRecyclerAdapter
    var mPlaceList = ArrayList<PlaceData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_place_list)
        setupEvents()
        setValues()
    }

    override fun onResume() {
        super.onResume()
        getMyPlaceListFromServer()
    }


    override fun setupEvents() {
        addBtn.setOnClickListener {

        }

    }

    override fun setValues() {
        titleTxt.text = ""
        addBtn.visibility = View.VISIBLE

        mPlaceRecycleAdapter = PlaceRecyclerAdapter(mContext, mPlaceList)
        binding.myPlaceRecyclerView.adapter = mPlaceRecycleAdapter
        binding.myPlaceRecyclerView.layoutManager = LinearLayoutManager(mContext)
//s
    }
    fun getMyPlaceListFromServer(){
        apiList.getRequestMyPlace().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if(response.isSuccessful) {
                    val br = response.body()!!
                    mPlaceList.clear()
                    mPlaceList.addAll(br.data.places)

                    mPlaceRecycleAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}