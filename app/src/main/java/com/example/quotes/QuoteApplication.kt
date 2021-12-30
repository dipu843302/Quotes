package com.example.quotes

import android.app.Application
import com.example.quotes.api.QuoteService
import com.example.quotes.api.RetrofitHelper
import com.example.quotes.db.QuoteDatabase
import com.example.quotes.repository.QuoteRepository

class QuoteApplication:Application() {
    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initalize()
    }

    private fun initalize() {
        val quoteService=RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database=QuoteDatabase.getDatabase(applicationContext)
         quoteRepository=QuoteRepository(quoteService,database,applicationContext)
    }
}