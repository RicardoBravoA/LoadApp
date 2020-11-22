package com.udacity.load.app.main

import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.load.app.data.listener.ProgressListener
import com.udacity.load.app.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment(), ProgressListener {

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application, this)).get(
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
            GlobalScope.launch(Dispatchers.Main) {
                mainViewModel.load("https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip")
            }

            /*GlobalScope.launch(Dispatchers.Main) {
                for (i in 0..100 step 10) {
                    binding.customAnimationView.setProgress(i.toFloat())
                    delay(1000L)
                }
                delay(1000)
                binding.view.visibility = View.VISIBLE
                binding.motionLayout.transitionToEnd()

            }*/

        }
        return binding.root
    }

    override fun progress(value: Long) {
        Log.i("z- progress", value.toString())
    }

}