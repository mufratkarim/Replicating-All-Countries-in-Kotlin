package com.mka.kotlincountries.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mka.kotlincountries.R
import com.mka.kotlincountries.model.Country
import com.mka.kotlincountries.model.Currencies
import com.mka.kotlincountries.model.Languages
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryHolder>() {

    fun updateCountries(newCountries: ArrayList<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )


    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

    class CountryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName = view.country
        val capital = view.capital
        val flag = view.imageView
        val currencies = view.currencies
        val languages = view.languages

        fun bind(country: Country) {
            countryName.text = country.countryName
            capital.text = String.format("Capital:\n" + country.capitalName)
            Picasso.get()
                .load(country.flag)
                .placeholder(android.R.drawable.stat_sys_download)
                .fit()
                .error(android.R.drawable.stat_notify_error)
                .into(flag)

            country.currencies!!.forEach { currency: Currencies ->
                val currencyText = String.format("Currency:\n" + currency.name + "(" + currency.symbol + ")")
                currencies.text = currencyText
            }

            country.languages!!.forEach { language: Languages ->
                val languageText = String.format("Languages:\n" + language.name + "(" + language.nativeName + ")")
                languages.text = languageText
            }

        }
    }
}