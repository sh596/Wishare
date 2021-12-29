package com.example.myapplication.screen.createwish

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.screen.mywish.MyWishViewModel
import java.lang.IllegalArgumentException

class CreateWishFactory(private val context : Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreateWishViewModel::class.java)){
            return CreateWishViewModel(context) as T
        }
        throw IllegalArgumentException("Exception")
    }
}