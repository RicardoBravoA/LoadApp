package com.udacity.load.app

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.udacity.load.app.databinding.LoadingButtonBinding

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: LoadingButtonBinding =
        LoadingButtonBinding.inflate(LayoutInflater.from(context), this, true)
    private var progressColor = ContextCompat.getColor(context, R.color.purple_700_50)

    @ColorInt
    var customBackgroundColor = ContextCompat.getColor(context, R.color.purple_500)
        set(value) {
            field = value
            binding.constraintLayout.setBackgroundColor(field)
            invalidate()
        }
    var defaultText: String = context.getString(R.string.download)
        set(value) {
            field = value
            invalidate()
        }
    var actionText: String = context.getString(R.string.we_are_loading)
        set(value) {
            field = value
            invalidate()
        }

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.LoadingButton, 0, 0
            )

            typedArray.also {
                if (it.hasValue(R.styleable.LoadingButton_lb_background)) {
                    customBackgroundColor = it.getColor(
                        R.styleable.LoadingButton_lb_background,
                        ContextCompat.getColor(context, R.color.purple_500)
                    )
                    setLoadingBackgroundColor(customBackgroundColor)
                }

                if (it.hasValue(R.styleable.LoadingButton_lb_default_text)) {
                    defaultText = it.getString(R.styleable.LoadingButton_lb_default_text).toString()
                }

                if (it.hasValue(R.styleable.LoadingButton_lb_action_text)) {
                    actionText = it.getString(R.styleable.LoadingButton_lb_action_text).toString()
                }

                val progressColor = typedArray.getColor(
                    R.styleable.LoadingButton_lb_progressColor,
                    ContextCompat.getColor(context, R.color.purple_700_50)
                )
                setProgressColor(progressColor)
                it.recycle()
            }
        }

        binding.customTextView.text = defaultText
        setProgressColor(progressColor)
    }

    fun onClick() {
        binding.customTextView.text = actionText
        binding.circularView.visibility = VISIBLE
        binding.circularView.progress(100f)
        binding.progressView.visibility = VISIBLE
        binding.progressView.setProgress(100)
    }

    fun clear() {
        binding.progressView.visibility = GONE
        binding.circularView.progress(0f, 0L)
        binding.circularView.visibility = GONE
        binding.customTextView.text = defaultText
    }

    fun complete() {
        binding.progressView.setProgress(100, false)
        binding.circularView.progress(100f, 0L)
        clear()
    }

    fun setProgressColor(color: Int) {
        binding.progressView.setProgressColor(color)
    }

    fun setLoadingBackgroundColor(@ColorInt color: Int) {
        binding.constraintLayout.setBackgroundColor(color)
    }

}