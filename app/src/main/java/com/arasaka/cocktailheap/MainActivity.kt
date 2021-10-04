package com.arasaka.cocktailheap

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.arasaka.cocktailheap.databinding.ActivityMainBinding
import com.arasaka.cocktailheap.presentation.BaseActivity
import com.arasaka.cocktailheap.presentation.cocktails.CocktailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun layoutId() = R.layout.activity_main;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
    }

    override val fragmentContainer: FragmentContainerView
        get() = binding.fcv

    override fun setUpNavigation(navController: NavController) =
        NavigationUI.setupWithNavController(binding.bnvMain, navController)


    override fun showProgress(show: Boolean) {
        binding.progressView.isVisible = show
    }

    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId())
    }


}