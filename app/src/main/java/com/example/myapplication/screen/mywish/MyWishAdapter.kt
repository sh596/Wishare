package com.example.myapplication.screen.mywish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemWishBinding
import com.example.myapplication.util.roomDB.Wish

class MyWishAdapter: RecyclerView.Adapter<MyWishAdapter.ViewHolder>() {
    private var list = ArrayList<Wish>()

    inner class ViewHolder(private val binding: ItemWishBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item : Wish){
            binding.itemWishContent.text = item.wishContent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWishBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setItem(list: ArrayList<Wish>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}