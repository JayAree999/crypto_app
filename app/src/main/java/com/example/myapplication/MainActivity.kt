package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CoinAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val coins = mutableListOf<Coin>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = CoinAdapter(coins)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        getCoinsFromAPI()
    }

    private fun getCoinsFromAPI() {
        val api = Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinAPI::class.java)

        val call = api.getCoins()
        call.enqueue(object : Callback<CoinResponse> {
            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    coins.addAll(body?.data?.coins?.map { Coin(it.name, it.symbol, it.price) } ?: emptyList())
                    viewAdapter.notifyDataSetChanged()
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }




}