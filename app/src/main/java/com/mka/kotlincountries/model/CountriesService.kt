package com.mka.kotlincountries.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    private val BASE_Url = "https://raw.githubusercontent.com/"
    private val api: CountriesApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)

    }

    fun getCountries() = api.getCountries()

}