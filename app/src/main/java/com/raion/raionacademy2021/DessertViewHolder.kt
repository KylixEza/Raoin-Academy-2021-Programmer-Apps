package com.raion.raionacademy2021

import androidx.recyclerview.widget.RecyclerView
import com.raion.raionacademy2021.databinding.ItemListDessertBinding

class DessertViewHolder(private val view: ItemListDessertBinding): RecyclerView.ViewHolder(view.root) {
    fun bind(dessert: Dessert) {
        view.tvName.text = dessert.nama
        view.ivImage.setImageResource(dessert.imageId)
    }
}