package com.mka.kotlincountries.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("mufratkarim/mufratkarim/main/countries_mka.json")
    fun getCountries(): Single<List<Country>>
}