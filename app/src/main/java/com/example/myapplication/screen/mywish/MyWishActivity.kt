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
import com.example.myapplication.util.roomDB.Wish

class MyWishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyWishBinding

    private val viewModel  by lazy {
        ViewModelProvider(this, MyWishViewModelFactory(this))[MyWishViewModel::class.java]
    }

    val itemClick: (Wish) -> Unit = { item : Wish ->
        viewModel.remove(item)
    }

    private val adapter = MyWishAdapter(itemClick)

    override fun onResume() {
        super.onResume()
        adapter.removeAll()
        viewModel.setList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wish)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_wish)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

        binding.myWishRecycler.adapter = adapter

        viewModel.myWishList.observe(this){
            adapter.setItem(viewModel.myWishList.value!!)
        }
        adapter.removeAll()
        viewModel.setList()
    }
    fun accessCreateWish(view: View){
        startActivity(Intent(this, CreateWishActivity::class.java))
    }
}