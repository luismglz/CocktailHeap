package com.arasaka.cocktailheap.core.platform

import android.content.Context
import com.arasaka.cocktailheap.core.extension.networkInfo
import javax.inject.Inject

//Injection with hilt
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting == true
}