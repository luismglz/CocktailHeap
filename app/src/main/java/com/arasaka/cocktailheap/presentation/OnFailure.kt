package com.arasaka.cocktailheap.presentation

import com.arasaka.cocktailheap.core.exception.Failure

interface OnFailure {

    fun handleFailure(failure: Failure?)
}