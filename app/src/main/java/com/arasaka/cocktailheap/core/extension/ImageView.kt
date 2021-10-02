package com.arasaka.cocktailheap.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.arasaka.cocktailheap.R

@BindingAdapter("loadFromURL")
fun ImageView.loadFromURL(url: String) = this.load(url){
    crossfade(true)
    placeholder(R.drawable.ic_cocktail)
    transformations(CircleCropTransformation())
}