package com.example.keepthetime.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime.R
import com.example.keepthetime.api.APIList
import com.example.keepthetime.api.ServerApi
import com.example.keepthetime.fragments.RequestFriendsListFragment
import com.example.keepthetime.models.BasicResponse
import com.example.keepthetime.models.UserData
import com.example.keepthetime.ui.settings.MyFriendsActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFriendsRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>,
    val type : String
) : RecyclerView.Adapter<MyFriendsRecyclerAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val profileImg = view.findViewById<ImageView>(R.id.profileImg)
        val nicknameTxt = view.findViewById<TextView>(R.id.nicknameTxt)
        val addFriendBtn = view.findViewById<Button>(R.id.addFriendBtn)
        val acceptBtn = view.findViewById<Button>(R.id.acceptBtn)
        val denyBtn = view.findViewById<Button>(R.id.denyBtn)
        val requestBtnLayout = view.findViewById<LinearLayout>(R.id.requestBtnLayout)

        fun bind (item : UserData) {

            val apiList = ServerApi.getRetrofit(mContext).create(APIList::class.java)

            Glide.with(mContext).load(item.profileImg).into(profileImg)
            nicknameTxt.text = item.nickname

            when (type) {
                "add" -> {
                    addFriendBtn.visibility = View.VISIBLE
                    requestBtnLayout.visibility = View.GONE
                }
                "requested" -> {
                    addFriendBtn.visibility = View.GONE
                    requestBtnLayout.visibility = View.VISIBLE
                }
                "my" -> {
                    addFriendBtn.visibility = View.GONE
                    requestBtnLayout.visibility = View.GONE
                }
            }

//            ?????? / ?????? ?????? ?????? ?????? ?????? ?????? => type??? ????????? ?????? ?????????
//            ????????? ?????????????????? ???????????? => ????????? ??????

            val ocl = object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    val okOrNO = p0!!.tag.toString()

//                    ??????????????? API ????????? ?????????
//                    1. ?????? ?????????
                    apiList.putRequestAnswerRequest(item.id, okOrNO).enqueue(object : Callback<BasicResponse>{
                        override fun onResponse(
                            call: Call<BasicResponse>,
                            response: Response<BasicResponse>
                        ) {
                            if (!response.isSuccessful) {
                                val errorBodyStr = response.errorBody()!!.string()
                                val jsonObj = JSONObject(errorBodyStr)
                                val message = jsonObj.getString("message")

                                Log.e("??????_?????? ??????", message)
                            }

//                            ?????????????????? ????????????(requested Friends List) ?????? ???????????? ????????? ???????
//                            ????????? -> ???????????? : context ?????? ??????


//                            ViewPager2?????? fragment??? ????????? ?????? ???????????? ????????? ??????
//                            ViewPager2????????? ????????? fragment??? ????????? ??? ??????. => tag ????????? ????????????.
                            ((mContext as MyFriendsActivity)
                                .supportFragmentManager
                                .findFragmentByTag("f1") as RequestFriendsListFragment)
                                .getRequestedFriendsListFromServer()
                        }

                        override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                        }
                    })
                }
            }

            acceptBtn.setOnClickListener(ocl)
            denyBtn.setOnClickListener(ocl)

            addFriendBtn.setOnClickListener {
                apiList.postRequestAddFriend(item.id).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                mContext,
                                "${item.nickname}????????? ??????????????? ???????????????.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.list_item_user, parent, false)
        return ItemViewHolder(row)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}