package com.example.myapplication.screen.mywish

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.util.roomDB.RoomUtil
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDatabase
import java.util.*
import kotlin.collections.ArrayList

class MyWishRepo(val context: Context) : RepoInterface{
    override suspend fun getMyWishList(): ArrayList<Wish> {
        return WishDatabase.getInstance(context).wishDao().getMineWish(RoomUtil.mine).toCollection(ArrayList())
    }
}