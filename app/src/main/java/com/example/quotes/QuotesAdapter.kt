package com.example.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.models.QuoteList
import com.example.quotes.models.Result
import kotlinx.android.synthetic.main.item_layout.view.*

class QuotesAdapter(private val list: List<Result>):RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return QuotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
       val quote=list[position]
        holder.setData(quote)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class QuotesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
     fun setData(result: Result){
         itemView.apply {
             text1.text=result.content
             text2.text=result.author
         }
     }
    }
}