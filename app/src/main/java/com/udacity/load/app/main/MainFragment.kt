package com.udacity.load.app.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.load.app.R
import com.udacity.load.app.databinding.FragmentMainBinding
import com.udacity.load.app.domain.model.ItemModel
import com.udacity.load.app.util.Constant

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var itemModel: ItemModel? = null

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(requireActivity().application)
        ).get(
            MainViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        mainViewModel.itemList.observe(viewLifecycleOwner, {
            showData(it)
        })

        binding.selectRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<View>(checkedId) as RadioButton
            val mySelectedIndex = radioButton.tag as Int

            itemModel =
                mainViewModel.itemList.value?.firstOrNull { mySelectedIndex == it.id }
        }

        mainViewModel.success.observe(viewLifecycleOwner, {
            if (it) {
                binding.loadingButton.complete()
            } else {
                binding.loadingButton.clear()
            }
        })

        binding.loadingButton.setOnClickListener {
            /*itemModel?.let {
                binding.loadingButton.onClick()
                mainViewModel.load(it)
            } ?: Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.error_select_group),
                Toast.LENGTH_SHORT
            ).show()*/
            binding.loadingButton.onClick()
//            binding.progressView.setProgress(100)
        }

        createChannel(
            getString(R.string.notification_channel_id),
            getString(R.string.notification_channel_name)
        )

        return binding.root
    }

    private fun showData(list: List<ItemModel>) {

        list.forEach {
            val radioButton = RadioButton(context)
            radioButton.text = it.description
            val textSize =
                (resources.getDimension(R.dimen.size_18) / resources.displayMetrics.scaledDensity)
            radioButton.textSize = textSize
            radioButton.tag = it.id

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

    private fun createChannel(channelId: String, channelName: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(false)
            }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description =
                getString(R.string.notification_channel_description)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

}