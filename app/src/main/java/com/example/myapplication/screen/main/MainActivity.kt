package com.example.myapplication.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDao
import com.example.myapplication.util.roomDB.WishDatabase
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    lateinit var wishDao : WishDao
    lateinit var wishListAdapter : WishListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        wishDao = WishDatabase.getInstance(application).wishDao()

        viewModel = ViewModelProvider(this, MainViewModelFactory(wishDao)).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        CoroutineScope(Dispatchers.IO).launch {
            val wishList = viewModel.getAlreadyWishList()
            if(wishList.isEmpty()) {
                wishListAdapter = WishListAdapter(mutableListOf())
            }   else{
                wishListAdapter = WishListAdapter(wishList.toList() as MutableList<Wish>)
            }

            Log.d("todayWish 리스트 전체", wishList.toString())


            withContext(Dispatchers.Main){

                wishListAdapter.notifyItemInserted(0)
                binding.recyclerMainWishList.adapter = wishListAdapter
            }
        }

        binding.btnMainToDayWish.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val todayWish = viewModel.getTodayWish()
                Log.d("todayWish", todayWish.toString())

                withContext(Dispatchers.Main){
                    Log.d("todayWish", "넣기 시작")
                    wishListAdapter.items.add(todayWish)
                    wishListAdapter.notifyItemInserted(0)
                    Log.d("todayWish", "끝")
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