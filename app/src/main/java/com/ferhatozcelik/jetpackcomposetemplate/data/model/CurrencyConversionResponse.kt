package com.ferhatozcelik.jetpackcomposetemplate.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    val date: String,
    val usd: UsdCurrency
)

data class UsdCurrency(
    @SerializedName("sek") val sek: Double? // Use Double? to handle potential null values
)