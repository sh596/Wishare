package com.example.myapplication.screen.createwish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreateWishBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CreateWishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateWishBinding

    private val viewModel by lazy {
        ViewModelProvider(this, CreateWishFactory(this))    [CreateWishViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wish)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_wish)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
    }
    fun createWish(view: View){
        if(viewModel.wishText.value!!.isNotEmpty()){
            viewModel.postWish()
            viewModel.saveWish()
            finish()

        }else{
            Toast.makeText(this, "소원을 적어주세요", Toast.LENGTH_SHORT).show()
        }
    }
}