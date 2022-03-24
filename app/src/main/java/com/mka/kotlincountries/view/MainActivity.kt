package com.mka.kotlincountries.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mka.kotlincountries.R
import com.mka.kotlincountries.model.Country
import com.mka.kotlincountries.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerView_countryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                recyclerView_countryList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it as ArrayList<Country>)
            }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let {
                error_textView.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar_countryList.visibility = if (it) View.VISIBLE else View.INVISIBLE
                if (it) {
                    error_textView.visibility = View.GONE
                    recyclerView_countryList.visibility = View.GONE
                }

            }
        })
    }
}