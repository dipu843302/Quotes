package com.example.quotes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.models.QuoteList
import com.example.quotes.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) :ViewModel(){

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getQuotes(1)
        }
    }
    fun get() = repository.getData()
}