package com.udacity.load.app

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.udacity.load.app.databinding.LoadingButtonBinding
import com.udacity.load.app.util.Constant

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: LoadingButtonBinding =
        LoadingButtonBinding.inflate(LayoutInflater.from(context), this, true)

    @ColorInt
    private var backgroundColor: Int? = null
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
        backgroundColor = ContextCompat.getColor(context, R.color.purple_700_50)
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.LoadingButton, 0, 0
        )

        typedArray.let {
            if (it.hasValue(R.styleable.LoadingButton_lb_background)) {
                backgroundColor = it.getColor(
                    R.styleable.LoadingButton_lb_background,
                    ContextCompat.getColor(context, R.color.purple_700_50)
                )
            }

            if (it.hasValue(R.styleable.LoadingButton_lb_default_text)) {
                defaultText = it.getString(R.styleable.LoadingButton_lb_default_text).toString()
            }

            if (it.hasValue(R.styleable.LoadingButton_lb_action_text)) {
                actionText = it.getString(R.styleable.LoadingButton_lb_action_text).toString()
            }

            typedArray.recycle()
        }

        binding.customTextView.text = defaultText
        binding.view.setBackgroundColor(backgroundColor!!)

        binding.motionLayout.setOnClickListener {
            binding.customTextView.text = actionText
            binding.circularView.progress(100f)

            binding.motionLayout.setTransition(R.id.transition_end)
            binding.motionLayout.setTransitionDuration(0)
            binding.motionLayout.transitionToEnd()

            binding.motionLayout.setTransition(R.id.transition_start)
            binding.motionLayout.setTransitionDuration(Constant.DURATION)
            binding.motionLayout.transitionToEnd()

        }
    }

    fun animateToEnd() {
        binding.motionLayout.progress = 1f
    }

}