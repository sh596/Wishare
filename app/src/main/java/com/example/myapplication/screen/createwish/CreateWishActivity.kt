package com.example.myapplication.screen.createwish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreateWishBinding

class CreateWishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateWishBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[CreateWishViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wish)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_wish)
        binding.viewModel = viewModel
    }
    fun createWish(view: View){
        if(viewModel.wishText.value!!.isNotEmpty()){
            viewModel.postWish()
            finish()
        }
    }
}