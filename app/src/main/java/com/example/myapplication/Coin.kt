package com.example.myapplication

data class CoinResponse(
    val data: CoinsData
)

data class CoinsData(
    val coins: List<Coin>
)

data class Coin(
    val name: String,
    val symbol: String,
    val price: Double
)