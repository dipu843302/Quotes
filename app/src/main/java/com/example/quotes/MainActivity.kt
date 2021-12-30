package com.example.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.api.QuoteService
import com.example.quotes.api.RetrofitHelper
import com.example.quotes.repository.QuoteRepository
import com.example.quotes.viewmodels.MainViewModel
import com.example.quotes.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repository=(application as QuoteApplication).quoteRepository

        mainViewModel=ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
           Toast.makeText(this,it.results.toString(), Toast.LENGTH_LONG).show()

        })
    }
}