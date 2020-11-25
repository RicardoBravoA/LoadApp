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
import com.udacity.load.app.R
import com.udacity.load.app.data.listener.ProgressListener
import com.udacity.load.app.databinding.FragmentMainBinding
import com.udacity.load.app.util.CircularViewAnimation
import com.udacity.load.app.util.Constant
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

        binding.customButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {

                binding.view.visibility = View.VISIBLE

                binding.motionLayout.setTransition(R.id.transition_end)
                binding.motionLayout.setTransitionDuration(0)
                binding.motionLayout.transitionToEnd()

                binding.motionLayout.setTransition(R.id.transition_start)
                binding.motionLayout.setTransitionDuration(Constant.DURATION)

                binding.customAnimationView.progress(100f)
                binding.motionLayout.transitionToEnd()

                /*
                mainViewModel.load("https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip")*/
            }

        }

        binding.progressButton.setOnClickListener {
            binding.motionLayout.progress = 1f
        }

        return binding.root
    }

    override fun load(progress: Int, contentLength: Long) {
        GlobalScope.launch(Dispatchers.Main) {

            if (contentLength != -1L) {
                val circleAnimation =
                    CircularViewAnimation(binding.customAnimationView, progress.toFloat())
                circleAnimation.duration = 1000
                binding.customAnimationView.startAnimation(circleAnimation)
                val newProgress = progress.toFloat() / 100f
                Log.i("z- progress", "$progress - $contentLength - $newProgress")

//                binding.motionLayout.progress = newProgress


            }
            if (progress == 100) {
                println("z- completed")
            }

        }

    }

}