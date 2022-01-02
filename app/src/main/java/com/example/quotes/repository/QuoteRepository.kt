package com.example.quotes.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotes.api.QuoteService
import com.example.quotes.db.QuoteDatabase
import com.example.quotes.models.QuoteList
import com.example.quotes.utils.NetworkUtils

class QuoteRepository(val quoteService: QuoteService,
                      private val quoteDatabase: QuoteDatabase
                      ,private val applicationContext: Context) {

    private val quotesLiveData:MutableLiveData<QuoteList> = MutableLiveData()



    fun getData():LiveData<QuoteList>{
        return quotesLiveData
    }
    suspend fun getQuotes(page:Int){
        if (NetworkUtils.isInternetAvailable(applicationContext)){
            val result=quoteService.getQuotes(page)
            if (result?.body()!=null){
                quoteDatabase.quoteDao().deleteQuotes()
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }else{
            val quotes=quoteDatabase.quoteDao().getQuotes()
            val quoteList=QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)
        }

    }
}