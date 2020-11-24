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
import com.udacity.load.app.util.CircularViewAnimation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : Fragment(), ProgressListener {

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action
    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application, this)).get(
            MainViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val circleAnimation = CircularViewAnimation(binding.customAnimationView, 360f)
        circleAnimation.duration = 1000

        binding.customButton.setOnClickListener {
            Log.i("z- data", "true")
            /*GlobalScope.launch {
                mainViewModel.load("https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip")
            }*/


            GlobalScope.launch(Dispatchers.Main) {
                /*for (i in 0..100 step 1) {
                    binding.customAnimationView.setProgress(i.toFloat())
                }*/
                binding.customAnimationView.startAnimation(circleAnimation)
//                delay(1000)
                binding.view.visibility = View.VISIBLE
                binding.motionLayout.transitionToEnd()

            }

        }
        return binding.root
    }

    override fun load(progress: Int, contentLength: Long) {
        GlobalScope.launch(Dispatchers.Main) {
            /*for (i in 0..100 step 10) {
                binding.customAnimationView.setProgress(i.toFloat())
            }*/
            /*binding.view.visibility = View.VISIBLE
            binding.motionLayout.transitionToEnd()*/
            if (contentLength != -1L) {
                Log.i("z- progress", "$progress - $contentLength")
//                binding.customAnimationView.setProgress(progress.toFloat())
            }
            if (progress == 100) {
                println("z- completed")
            }

        }

    }

}