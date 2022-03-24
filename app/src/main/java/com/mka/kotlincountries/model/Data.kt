package com.mka.kotlincountries.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val countryName: String?,
    @SerializedName("capital") val capitalName: String?,
    @SerializedName("flagPNG") val flag: String?,
    @SerializedName("languages") val languages: List<Languages>? = listOf(),
    @SerializedName("currencies") val currencies: List<Currencies>? = listOf()
)

data class Languages(
    @SerializedName("name") val name: String?,
    @SerializedName("nativeName") val nativeName: String?
)

data class Currencies(
    @SerializedName("name") val name: String?,
    @SerializedName("symbol") val symbol: String?
)