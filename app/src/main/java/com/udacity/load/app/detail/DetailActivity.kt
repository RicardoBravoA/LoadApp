package com.udacity.load.app.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.udacity.load.app.R
import com.udacity.load.app.databinding.ActivityDetailBinding
import com.udacity.load.app.domain.model.DetailModel
import com.udacity.load.app.util.Constant

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        intent?.extras?.let {
            val detailModel = it.getParcelable<DetailModel>(Constant.DATA)
            detailModel?.let { detailModel ->
                binding.contentDetail.fileNameValueTextView.text =
                    detailModel.description
                if (detailModel.status) {
                    binding.contentDetail.statusValueTextView.text = getString(R.string.success)
                } else {
                    binding.contentDetail.statusValueTextView.text = getString(R.string.fail)
                    binding.contentDetail.statusValueTextView.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.fail
                        )
                    )
                }

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}