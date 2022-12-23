package com.example.chat.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.databinding.ItemCountBinding

class RecyclerAdapter : RecyclerView.
Adapter<RecyclerAdapter.ViewHolder>() {

    private var list: List<String> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listData: MutableList<String>) {
        this.list = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(string: String) {
            binding.txtItemCount.text = string
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCountBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
}