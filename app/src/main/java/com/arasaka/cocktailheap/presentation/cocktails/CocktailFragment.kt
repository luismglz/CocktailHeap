package com.arasaka.cocktailheap.presentation.cocktails

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.arasaka.cocktailheap.BuildConfig
import com.arasaka.cocktailheap.R
import com.arasaka.cocktailheap.core.extension.failure
import com.arasaka.cocktailheap.core.extension.observe
import com.arasaka.cocktailheap.data.api.CocktailApi
import com.arasaka.cocktailheap.data.source.CocktailRepositoryImpl
import com.arasaka.cocktailheap.databinding.CocktailFragmentBinding
import com.arasaka.cocktailheap.domain.model.Cocktail
import com.arasaka.cocktailheap.framework.ApiProvider
import com.arasaka.cocktailheap.presentation.BaseFragment
import com.arasaka.cocktailheap.presentation.BaseViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

@DelicateCoroutinesApi
@WithFragmentBindings
@AndroidEntryPoint
class CocktailFragment : BaseFragment(R.layout.cocktail_fragment){
    private lateinit var binding: CocktailFragmentBinding
    private lateinit var adapter: CocktailAdapter


    private val cocktailViewModel by viewModels<CocktailViewModel>(); //view model injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel.apply {
            observe(state, ::onViewStateChanged)//Observe when livedata is modified
            failure(failure, ::handleFailure)

            doGetCocktailsByName("")
            //cocktailViewModel.doGetCocktailsByName("")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchInput: SearchView = requireView().findViewById(R.id.svCocktail)

        searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            //ENTER BUTTON IN KEYBOARD (submit search)
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query!=null){
                    binding.rcCocktails.scrollToPosition(0)
                    cocktailViewModel.doGetCocktailsByName(query.lowercase())
                    //searchInput.clearFocus() ->Hide keyboard at type key...
                }
                return true
            }
        })
    }


    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CocktailViewState.CocktailsReceived -> setUpAdapter(state.cocktails)
        }
    }


    private fun setUpAdapter(cocktails: List<Cocktail>){
        adapter = CocktailAdapter();
        adapter.addData(cocktails);
        binding.rcCocktails.apply {
            adapter = this@CocktailFragment.adapter
        }

    }

    override fun setBinding(view: View) {
        binding = CocktailFragmentBinding.bind(view)
        binding.lifecycleOwner = this
    }

}