package com.raion.raionacademy2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.raion.raionacademy2021.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var listBinding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listBinding = ActivityListBinding.inflate(layoutInflater)
        setContentView(listBinding.root)

        val dessertAdapter = DessertAdapter()

        listBinding.rvDessert.apply {
            adapter = dessertAdapter
            layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
        }

        dessertAdapter.setAllData(Dummy.getAllDesserts())
    }
}