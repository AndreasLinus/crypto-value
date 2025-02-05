package com.ferhatozcelik.jetpackcomposetemplate.data.model

import com.google.gson.annotations.SerializedName

data class CryptoData(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("baseAsset") val baseAsset: String,
    @SerializedName("quoteAsset") val quoteAsset: String,
    @SerializedName("openPrice") val openPrice: String,
    @SerializedName("lowPrice") val lowPrice: String,
    @SerializedName("highPrice") val highPrice: String,
    @SerializedName("lastPrice") val lastPrice: String,
    @SerializedName("volume") val volume: String,
    @SerializedName("bidPrice") val bidPrice: String,
    @SerializedName("askPrice") val askPrice: String,
    @SerializedName("at") val at: Long
)
