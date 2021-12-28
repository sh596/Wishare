package com.example.myapplication.screen.main

import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDao
import com.example.myapplication.util.roomDB.WishDatabase
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    lateinit var wishDao : WishDao
    lateinit var wishListAdapter : WishListAdapter

    private lateinit var pref : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        wishDao = WishDatabase.getInstance(application).wishDao()

        viewModel = ViewModelProvider(this, MainViewModelFactory(wishDao)).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        editor = pref.edit()

//        WishDatabase.getInstance(this).wishDao().clear()


        CoroutineScope(Dispatchers.IO).launch {
            val wishList = viewModel.getAlreadyWishList()
            if(wishList.isEmpty()) {
                wishListAdapter = WishListAdapter(mutableListOf())
            }   else{
                wishListAdapter = WishListAdapter(wishList.toList().map { it.wishContent } as MutableList<String>)
            }

            withContext(Dispatchers.Main){
                wishListAdapter.notifyItemInserted(0)
                binding.recyclerMainWishList.adapter = wishListAdapter
            }
        }

        binding.btnMainToDayWish.setOnClickListener{
            if ("1" == pref.getString(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"-r", "0")){
                Toast.makeText(this, "하루에 하나의 소원을 볼 수 있습니다.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            CoroutineScope(Dispatchers.IO).launch {
                val todayWish = viewModel.getTodayWish()

                Log.d("todayWish", todayWish.toString())

                editor.putString(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"-r", "1")
                editor.apply()

                withContext(Dispatchers.Main){
                    wishListAdapter.items.add(todayWish.wishContent)
                    wishListAdapter.notifyItemInserted(0)
                }
            }
        }
    }
}

class MainViewModelFactory(val wishDao : WishDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            MainActivityViewModel(wishDao) as T
        }   else{
            throw IllegalAccessException()
        }
    }
}