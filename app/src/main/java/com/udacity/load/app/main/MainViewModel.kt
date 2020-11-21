package com.udacity.load.app.main

import androidx.lifecycle.ViewModel
import com.udacity.load.app.domain.usecase.DownloadUseCase

class MainViewModel(private val downloadUseCase: DownloadUseCase) : ViewModel() {

}