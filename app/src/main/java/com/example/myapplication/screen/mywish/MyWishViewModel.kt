package com.example.myapplication.screen.mywish

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyWishViewModel(private val context: Context) : ViewModel() {
    private val repo: RepoInterface = MyWishRepo(context)
    val myWishList = MutableLiveData<ArrayList<Wish>>()

    init {
        setList()
    }

    fun setList() {
        viewModelScope.launch(Dispatchers.IO) {
            myWishList.postValue(repo.getMyWishList())
            Log.d(TAG, "setList: ${myWishList.value}")
        }
    }
//    fun remove(item: Wish){
//        viewModelScope.launch(Dispatchers.IO) {
//            WishDatabase.getInstance(context).wishDao().delete(item)
//        }
//    }
}