package com.raion.raionacademy2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import com.raion.raionacademy2021.databinding.ActivityMainBinding

val KEY_KEUNTUNGAN = "keuntungan"
val KEY_TERJUAL = "terjual"

class MainActivity : AppCompatActivity() {

    private var keuntungan = 0
    private var terjual = 0

    /*private lateinit var cakeButton : ImageButton
    private lateinit var textViewTerjual : TextView
    private lateinit var textViewKeuntungan : TextView*/

    private var kueSekarang = Dummy.getAllDesserts()[0]

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        Log.i("Lifecycle:", "onCreate terpanggil")

/*        cakeButton = findViewById(R.id.ib_cupcake)
        textViewTerjual = findViewById(R.id.tv_amount_sell)
        textViewKeuntungan = findViewById(R.id.tv_revenue)*/

        if (savedInstanceState != null) {
            val keuntunganLama = savedInstanceState.getInt(KEY_KEUNTUNGAN)
            val terjualLama = savedInstanceState.getInt(KEY_TERJUAL)

            keuntungan = keuntunganLama
            terjual = terjualLama
        }

        mainBinding.tvAmountSell.text = terjual.toString()
        mainBinding.tvRevenue.text = keuntungan.toString()

        mainBinding.ibCupcake.setOnClickListener {
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

        mainBinding.tvAmountSell.text = terjual.toString()
        mainBinding.tvRevenue.text = String.format("$%d", keuntungan)

        // Show the next dessert
        showCurrentDessert()
    }

    private fun showCurrentDessert() {
        var newDessert = Dummy.getAllDesserts()[0]
        for (dessert in Dummy.getAllDesserts()) {
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
            mainBinding.ibCupcake.setImageResource(newDessert.imageId)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dessert_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.list_menu -> {
                val intent = Intent(this@MainActivity, ListActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}