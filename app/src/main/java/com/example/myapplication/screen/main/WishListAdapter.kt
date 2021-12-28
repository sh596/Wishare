package com.example.myapplication.screen.main

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.util.roomDB.Wish

class WishListAdapter(val items : MutableList<String>) : RecyclerView.Adapter<WishListAdapter.WishListAdapterHolder>() {
    class WishListAdapterHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val content = view.findViewById<TextView>(R.id.txt_wishList_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapterHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_wish_list_item, parent, false)
        return WishListAdapterHolder(layout)
    }

    override fun onBindViewHolder(holder: WishListAdapterHolder, position: Int) {
        holder.content.text = items[position]//.wishContent
    }

    override fun getItemCount(): Int {
        return items.size
    }
}