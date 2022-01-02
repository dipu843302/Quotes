package com.example.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.api.QuoteService
import com.example.quotes.api.RetrofitHelper
import com.example.quotes.models.Result
import com.example.quotes.repository.QuoteRepository
import com.example.quotes.utils.NetworkUtils.Companion.isInternetAvailable
import com.example.quotes.viewmodels.MainViewModel
import com.example.quotes.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var quotesAdapter: QuotesAdapter
    lateinit var quoteRepository: QuoteRepository
    var quoteList= mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repository=(application as QuoteApplication).quoteRepository

        mainViewModel=ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)




        mainViewModel.get().observe(this, Observer {
            it?.let {
                quoteList.clear()

                quoteList.addAll(it.results)
                setAdapter()

            }



        })
    }

    private fun setAdapter() {
        recyclerView.adapter=QuotesAdapter(quoteList)
        recyclerView.layoutManager=LinearLayoutManager(this)

    }
}