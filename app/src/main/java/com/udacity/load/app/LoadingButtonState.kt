package com.udacity.load.app

sealed class LoadingButtonState {
    object Click : LoadingButtonState()
    object Loading : LoadingButtonState()
    object Complete : LoadingButtonState()
}