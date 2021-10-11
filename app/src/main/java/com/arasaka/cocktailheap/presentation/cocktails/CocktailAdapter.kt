package com.arasaka.cocktailheap.presentation.cocktails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arasaka.cocktailheap.core.utils.LayoutType
import com.arasaka.cocktailheap.databinding.GridCocktailBinding
import com.arasaka.cocktailheap.databinding.RowCocktailBinding
import com.arasaka.cocktailheap.domain.model.Cocktail

//ViewHolders: Fill the View elements in UI
@SuppressLint("NotifyDataSetChanged")
class CocktailAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Cocktails list initializes empty
    private var list: MutableList<Cocktail> = mutableListOf()
    var layoutType = LayoutType.LINEAR



    fun addData(list: List<Cocktail>){
        this.list = list.toMutableList()
        notifyDataSetChanged(); //to say that something changes in the list and show in row info
    }

    fun changeView(layoutType: LayoutType){
        this.layoutType = layoutType
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int)=layoutType.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType){
        LayoutType.LINEAR.ordinal ->ViewHolderItem(
            RowCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        else  -> {
            ViewHolderGridItem(GridCocktailBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

    }



    //bind information to rows, with respect to datacollection response
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BaseViewHolder).bind(
            list[position]
        )

    //this is to know how many items are in recyclerview
    override fun getItemCount() = list.size


    class ViewHolderItem(private val binding: RowCocktailBinding) :
        BaseViewHolder(binding.root){
        //this cocktail type object will be binded in recycleview
        override fun bind(data: Cocktail){
           // binding.txvName.text
            binding.item = data// 'item' was created in layout row

        }
    }

    class ViewHolderGridItem(private val binding: GridCocktailBinding) :
        BaseViewHolder(binding.root) {
        //this cocktail type object will be binded in recycleview
        override fun bind(data: Cocktail){
            // binding.txvName.text
            binding.item = data// 'item' was created in layout row

        }
    }

    abstract class BaseViewHolder(private val root: View):RecyclerView.ViewHolder(root){
        abstract fun bind(data: Cocktail)
    }

}