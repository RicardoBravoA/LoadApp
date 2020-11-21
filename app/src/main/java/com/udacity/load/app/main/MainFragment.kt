package com.udacity.load.app.main

import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import com.udacity.load.app.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action

    private val mainViewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application)).get(
            MainViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding =
            FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.customButton.setOnClickListener {
            Log.i("z- data", "true")
            mainViewModel.load("https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip")
            mainViewModel.abc()
        }

        return binding.root
    }

}