package com.example.myapplication.screen.mywish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMyWishBinding
import com.example.myapplication.screen.createwish.CreateWishActivity

class MyWishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyWishBinding

    private val viewModel  by lazy {
        ViewModelProvider(this)[MyWishViewModel::class.java]
    }

    private val adapter = MyWishAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wish)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_wish)
        binding.viewModel
        binding.lifecycleOwner = this

        binding.myWishRecycler.adapter = adapter
        adapter.setItem(viewModel.myWishList.value)
    }
    fun accessCreateWish(view: View){
        startActivity(Intent(this, CreateWishActivity::class.java))
    }
}