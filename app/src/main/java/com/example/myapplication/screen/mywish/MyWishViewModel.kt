package com.example.myapplication.screen.mywish

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.util.roomDB.Wish
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyWishViewModel(context:Context) : ViewModel() {
    private val repo : RepoInterface = MyWishRepo(context)
    val myWishList = MutableLiveData<ArrayList<Wish>>()

    init {
        viewModelScope.async {
            myWishList.value = repo.getMyWishList()
        }
    }
}