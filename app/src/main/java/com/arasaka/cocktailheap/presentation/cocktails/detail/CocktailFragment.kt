package com.arasaka.cocktailheap.presentation.cocktails.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arasaka.cocktailheap.R
import com.arasaka.cocktailheap.core.exception.Failure
import com.arasaka.cocktailheap.core.extension.failure
import com.arasaka.cocktailheap.core.extension.observe
import com.arasaka.cocktailheap.databinding.CocktailFragmentBinding
import com.arasaka.cocktailheap.domain.model.Cocktail
import com.arasaka.cocktailheap.presentation.BaseFragment
import com.arasaka.cocktailheap.presentation.BaseViewState

class CocktailFragment : BaseFragment(R.layout.cocktail_fragment) {
    private lateinit var binding: CocktailFragmentBinding
    private lateinit var adapter: CocktailAdapter
    private val cocktailViewModel by viewModels<CocktailViewModel>(); //view model injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel.apply {
            observe(state, ::onViewStateChanged)//Observe when livedata is modified
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state);
        when(state){

        }
    }

    private fun setUpAdapter(cocktails: List<Cocktail>){
        adapter = CocktailAdapter();
        adapter.addData(cocktails);
        binding.rcCocktails.apply {
            adapter = this@CocktailFragment.adapter;
        }
    }

    override fun setBinding(view: View) {
        binding = CocktailFragmentBinding.bind(view);
        binding.lifecycleOwner = this;
    }


}