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
    var customBackgroundColor: Int? = null
        set(value) {
            field = value
            value?.let {
                binding.constraintLayout.setBackgroundColor(it)
            }
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
        customBackgroundColor = ContextCompat.getColor(context, R.color.purple_700)
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
                        ContextCompat.getColor(context, R.color.purple_700)
                    )
                    setLoadingBackgroundColor(customBackgroundColor!!)
                }

                if (it.hasValue(R.styleable.LoadingButton_lb_default_text)) {
                    defaultText = it.getString(R.styleable.LoadingButton_lb_default_text).toString()
                }

                if (it.hasValue(R.styleable.LoadingButton_lb_action_text)) {
                    actionText = it.getString(R.styleable.LoadingButton_lb_action_text).toString()
                }

                val customProgressColor = typedArray.getColor(
                    R.styleable.LoadingButton_lb_progressColor,
                    progressColor
                )
                setProgressColor(customProgressColor)
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

//        binding.motionLayout.setTransition(R.id.transition_start)
//        binding.motionLayout.setTransitionDuration(Constant.DURATION)
//        binding.motionLayout.transitionToEnd()

        binding.progressView.setProgress(100)

    }

    fun clear() {
//        binding.motionLayout.setTransition(R.id.transition_end)
//        binding.motionLayout.setTransitionDuration(0)
//        binding.motionLayout.transitionToEnd()

//        binding.progressView.visibility = GONE

        binding.circularView.progress(0f, 0L)
        binding.circularView.visibility = GONE
        binding.customTextView.text = defaultText
    }

    fun complete() {
//        binding.progressView.setProgress(100, false)
        binding.circularView.progress(100f, 0L)
        clear()
    }

    fun setProgressColor(color: Int) {
//        binding.progressView.setProgressColor(color)
    }

    fun setLoadingBackgroundColor(@ColorInt color: Int) {
        binding.constraintLayout.setBackgroundColor(color)
    }

}