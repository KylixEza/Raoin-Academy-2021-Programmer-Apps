package com.raion.raionacademy2021

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raion.raionacademy2021.databinding.ItemListDessertBinding

class DessertAdapter: RecyclerView.Adapter<DessertViewHolder>() {

    private val listOfDesserts = ArrayList<Dessert>()

    fun setAllData(list: List<Dessert>) {
        listOfDesserts.clear()
        listOfDesserts.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertViewHolder {
        val view = ItemListDessertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DessertViewHolder(view)
    }

    override fun onBindViewHolder(holder: DessertViewHolder, position: Int) {
        holder.bind(listOfDesserts[position])
    }

    override fun getItemCount(): Int = listOfDesserts.size
}