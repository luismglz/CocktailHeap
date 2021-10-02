package com.arasaka.cocktailheap.presentation.cocktails.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arasaka.cocktailheap.databinding.RowCocktailBinding
import com.arasaka.cocktailheap.domain.model.Cocktail

//ViewHolders: Fill the View elements in UI

class CocktailAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Cocktails list initializes empty
    private var list: MutableList<Cocktail> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<Cocktail>){
        this.list = list.toMutableList()
        notifyDataSetChanged(); //to say that something changes in the list and show in row info
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderItem(
        RowCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    //bind information to rows, with respect to datacollection response
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderItem).bind(
            list[position]//the information to be printed is being passed on
        )

    //this is to know how many items are in recyclerview
    override fun getItemCount() = list.size;


    class ViewHolderItem(private val binding: RowCocktailBinding):RecyclerView.ViewHolder(binding.root){
        //this cocktail type object will be binded in recycleview
        fun bind(data: Cocktail){
           // binding.txvName.text
            binding.item = data// 'item' was created in layout row
        }
    }
}