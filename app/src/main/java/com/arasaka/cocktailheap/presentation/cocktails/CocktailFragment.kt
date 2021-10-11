package com.arasaka.cocktailheap.presentation.cocktails

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arasaka.cocktailheap.R
import com.arasaka.cocktailheap.core.extension.failure
import com.arasaka.cocktailheap.core.extension.observe
import com.arasaka.cocktailheap.databinding.CocktailFragmentBinding
import com.arasaka.cocktailheap.domain.model.Cocktail
import com.arasaka.cocktailheap.presentation.BaseFragment
import com.arasaka.cocktailheap.presentation.BaseViewState
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi
import com.arasaka.cocktailheap.core.utils.LayoutType


@DelicateCoroutinesApi
@WithFragmentBindings
@AndroidEntryPoint
class CocktailFragment : BaseFragment(R.layout.cocktail_fragment) {
    private lateinit var binding: CocktailFragmentBinding
    private val adapter: CocktailAdapter by lazy {CocktailAdapter()}
    private var gridLayoutManager:GridLayoutManager?=null


    private val cocktailViewModel by viewModels<CocktailViewModel>(); //view model injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cocktailViewModel.apply {
            observe(state, ::onViewStateChanged)//Observe when livedata is modified
            failure(failure, ::handleFailure)

            doGetCocktailsByName("")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val searchInput: SearchView = requireView().findViewById(R.id.svCocktail)

        val btnChangeView: FloatingActionButton =
            requireView().findViewById(R.id.floatingViewChange)

        val rv: RecyclerView = requireView().findViewById(R.id.rcCocktails)


        /*
        searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            //ENTER BUTTON IN KEYBOARD (submit search)
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    binding.rcCocktails.scrollToPosition(0)
                    cocktailViewModel.doGetCocktailsByName(query.lowercase())
                    //searchInput.clearFocus() ->Hide keyboard at type key...

                }
                return true
            }
        })*/

        /*
        //previous change view
        btnChangeView.setOnClickListener {
            setViews(rv, btnChangeView)
        }*/

    }




    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CocktailViewState.CocktailsReceived -> setUpAdapter(state.cocktails)
        }
    }


    private fun setUpAdapter(cocktails: List<Cocktail>) {
        binding.emptyView.isVisible = cocktails.isEmpty()
        adapter.addData(cocktails);
        binding.rcCocktails.apply {

            isVisible = cocktails.isNotEmpty()
            adapter = this@CocktailFragment.adapter
        }

    }

    override fun setBinding(view: View) {
        binding = CocktailFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.svCocktail.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            //ENTER BUTTON IN KEYBOARD (submit search)
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    binding.rcCocktails.scrollToPosition(0)
                    cocktailViewModel.doGetCocktailsByName(query.lowercase()?:"")
                    //searchInput.clearFocus() ->Hide keyboard at type key...
                }
                return true
            }
        })

        binding.floatingViewChange.setOnClickListener{
            val newLayout = if(adapter.layoutType == LayoutType.LINEAR){
                binding.rcCocktails.layoutManager = GridLayoutManager(requireContext(),3);
                LayoutType.GRID

            }else{
                binding.rcCocktails.layoutManager = LinearLayoutManager(requireContext());
                LayoutType.LINEAR
            }
            adapter.changeView(newLayout)
        }
    }

}