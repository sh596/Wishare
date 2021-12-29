package com.example.myapplication.screen.mywish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.example.myapplication.databinding.ItemWishBinding
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDatabase

class MyWishAdapter( val clickItem:(Wish) -> Unit): RecyclerView.Adapter<MyWishAdapter.ViewHolder>() {
    private var list = ArrayList<Wish>()

    inner class ViewHolder(private val binding: ItemWishBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item : Wish, position: Int){
            binding.itemWishContent.text = item.wishContent

            binding.root.setOnClickListener {
                clickItem(item)
                list.removeAt(position)
                notifyItemRemoved(position)
            }
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
        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int = list.size

    fun setItem(list: ArrayList<Wish>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    fun removeAll(){
        list = ArrayList()
        notifyDataSetChanged()
    }
}