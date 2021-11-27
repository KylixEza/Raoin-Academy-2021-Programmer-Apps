package com.raion.raionacademy2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

val KEY_KEUNTUNGAN = "keuntungan"
val KEY_TERJUAL = "terjual"
class MainActivity : AppCompatActivity() {

    private var keuntungan = 0
    private var terjual = 0

    private lateinit var cakeButton : ImageButton
    private lateinit var textViewTerjual : TextView
    private lateinit var textViewKeuntungan : TextView

    data class Dessert(
        val imageId : Int,
        val harga : Int,
        val minimumTerjual : Int
    )

    private val allDesserts = listOf(
        Dessert(R.drawable.cupcake, 5, 0),
        Dessert(R.drawable.donut, 10, 5),
        Dessert(R.drawable.eclair, 15, 20),
        Dessert(R.drawable.froyo, 30, 50),
        Dessert(R.drawable.gingerbread, 50, 100),
        Dessert(R.drawable.honeycomb, 100, 200),
        Dessert(R.drawable.icecreamsandwich, 500, 500),
        Dessert(R.drawable.jellybean, 1000, 1000),
        Dessert(R.drawable.kitkat, 2000, 2000),
        Dessert(R.drawable.lollipop, 3000, 4000),
        Dessert(R.drawable.marshmallow, 4000, 8000),
        Dessert(R.drawable.nougat, 5000, 16000),
        Dessert(R.drawable.oreo, 6000, 20000)
    )

    private var kueSekarang = allDesserts[0]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Lifecycle:", "onCreate terpanggil")

        cakeButton = findViewById(R.id.ib_cupcake)
        textViewTerjual = findViewById(R.id.tv_amount_sell)
        textViewKeuntungan = findViewById(R.id.tv_revenue)

        if (savedInstanceState != null) {
            val keuntunganLama = savedInstanceState.getInt(KEY_KEUNTUNGAN)
            val terjualLama = savedInstanceState.getInt(KEY_TERJUAL)

            keuntungan = keuntunganLama
            terjual = terjualLama
        }

        textViewTerjual.text = terjual.toString()
        textViewKeuntungan.text = keuntungan.toString()

        cakeButton.setOnClickListener {
            Log.i("Cake Button", "Tombol terclick")
            onDessertClicked()
        }
    }


    /**
     * Called when the user navigates away from the app but might come back
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_KEUNTUNGAN, keuntungan)
        outState.putInt(KEY_TERJUAL, terjual)
        Log.i("Lifecycle: ", "onSaveInstanceState Called")
        super.onSaveInstanceState(outState)
    }


    private fun onDessertClicked() {

        // Update the score
        keuntungan += 5
        terjual++

        textViewTerjual.text = terjual.toString()
        textViewKeuntungan.text = String.format("$%d", keuntungan)

        // Show the next dessert
        showCurrentDessert()
    }

    private fun showCurrentDessert() {
        var newDessert = allDesserts[0]
        for (dessert in allDesserts) {
            if (terjual >= dessert.minimumTerjual) {
                newDessert = dessert
            }
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            else break
        }

        // If the new dessert is actually different than the current dessert, update the image
        if (newDessert != kueSekarang) {
            kueSekarang = newDessert
            cakeButton.setImageResource(newDessert.imageId)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle:", "onStart terpanggil")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle:", "onResume terpanggil")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle:", "onPause terpanggil")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle:", "onStop terpanggil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle:", "onDestroy terpanggil")
    }
}