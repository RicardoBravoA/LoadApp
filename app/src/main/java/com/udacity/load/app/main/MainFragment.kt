package com.udacity.load.app.main

import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.load.app.R
import com.udacity.load.app.data.listener.ProgressListener
import com.udacity.load.app.databinding.FragmentMainBinding
import com.udacity.load.app.domain.model.ItemModel


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

        /*binding.customButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {

                binding.customAnimationView.progress(100f)

                *//*
                mainViewModel.load("https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip")*//*
            }

        }

        binding.progressButton.setOnClickListener {
            binding.loadingButton.complete()
            binding.customAnimationView.progress(100f, 0L)
        }*/

        mainViewModel.itemList.observe(viewLifecycleOwner, {
            showData(it)
        })

        return binding.root
    }

    private fun showData(list: List<ItemModel>) {

        list.forEach {
            val radioButton = RadioButton(context)
            radioButton.text = it.description
            val textSize =
                (resources.getDimension(R.dimen.size_18) / resources.displayMetrics.scaledDensity)
            radioButton.textSize = textSize

            val params = RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            val margin = requireContext().resources.getDimension(R.dimen.padding_8).toInt()

            params.setMargins(margin, margin, margin, margin)
            radioButton.layoutParams = params

            binding.selectRadioGroup.addView(radioButton)
        }

    }

    override fun load(progress: Int, contentLength: Long) {
        /*GlobalScope.launch(Dispatchers.Main) {

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

        }*/

    }

}