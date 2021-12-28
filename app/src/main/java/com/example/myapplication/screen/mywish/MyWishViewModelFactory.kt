package com.example.myapplication.screen.mywish

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MyWishViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyWishViewModel::class.java)){
            return MyWishViewModel(context) as T
        }
        throw IllegalArgumentException("Exception")
    }
}