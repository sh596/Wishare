package com.example.myapplication.screen.createwish

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.util.FireBaseUtil
import java.util.*

class CreateWishViewModel : ViewModel() {
    val wishText = MutableLiveData<String>()

    fun postWish(){
        val data = hashMapOf(
            "wish" to wishText
        )
        FireBaseUtil.getDB().collection("wish").document().set(data)

    }
}