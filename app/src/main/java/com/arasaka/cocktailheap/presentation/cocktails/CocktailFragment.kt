package com.arasaka.cocktailheap.presentation.cocktails

import android.graphics.Bitmap
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
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
import org.w3c.dom.Text
import java.lang.reflect.Modifier
import android.util.DisplayMetrics
import android.view.Display
import android.view.View.MeasureSpec
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView


@DelicateCoroutinesApi
@WithFragmentBindings
@AndroidEntryPoint
class CocktailFragment : BaseFragment(R.layout.cocktail_fragment) {
    private lateinit var binding: CocktailFragmentBinding
    private lateinit var adapter: CocktailAdapter
    private var gridLayoutManager:GridLayoutManager?=null


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

        val btnChangeView: FloatingActionButton =
            requireView().findViewById(R.id.floatingViewChange)

        val rv: RecyclerView = requireView().findViewById(R.id.rcCocktails)


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
        })

        btnChangeView.setOnClickListener {
            setViews(rv, btnChangeView)
        }

    }

    fun setViews(rv: RecyclerView, btnChangeView: FloatingActionButton) {
        if (rv.layoutManager is GridLayoutManager) {
            rv.layoutManager = LinearLayoutManager(context)
            btnChangeView.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_grid
                )
            )
        } else {
            gridLayoutManager = GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false)
            //rv.layoutManager = GridLayoutManager(context, 2)
            rv.layoutManager = gridLayoutManager
            rv.setHasFixedSize(true)
            btnChangeView.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_row
                )
            )
        }
    }


    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CocktailViewState.CocktailsReceived -> setUpAdapter(state.cocktails)
        }
    }


    private fun setUpAdapter(cocktails: List<Cocktail>) {
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