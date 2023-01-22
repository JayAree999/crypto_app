package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface CoinAPI {
    @GET("coins")
    fun getCoins(): Call<CoinResponse>
}