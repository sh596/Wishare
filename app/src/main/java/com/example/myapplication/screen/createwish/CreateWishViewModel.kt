package com.example.myapplication.screen.createwish

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.example.myapplication.util.FireBaseUtil
import com.example.myapplication.util.roomDB.RoomUtil
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class CreateWishViewModel(private val context: Context) : ViewModel() {
    private var information: Int? = null

    val wishText = MutableLiveData<String>()

    fun postWish() {
        val data = hashMapOf(
            "wish" to wishText
        )
        FireBaseUtil.getDB().collection("wish")
            .document("information")
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    information = it.result.data?.get("information").toString().toInt()
                    FireBaseUtil.getDB().collection("wish").document(information.toString())
                        .set(data)
                    val informationData = hashMapOf(
                        "information" to information!! + 1
                    )
                    FireBaseUtil.getDB().collection("wish").document("information")
                        .set(informationData)
                }
            }
    }
    fun saveWish(){
        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                WishDatabase.getInstance(context).wishDao().insert(Wish(wishContent = wishText.value!!,mine = RoomUtil.mine))
            }
        }
    }
}

