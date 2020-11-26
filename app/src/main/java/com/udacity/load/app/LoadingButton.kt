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
    var defaultText: String

    init {
        backgroundColor = ContextCompat.getColor(context, R.color.purple_700_25)
        defaultText = context.getString(R.string.download)
        init(attrs)
    }

    fun onClick() {
        binding.motionLayout.setTransition(R.id.transition_end)
        binding.motionLayout.setTransitionDuration(0)
        binding.motionLayout.transitionToEnd()

        binding.motionLayout.setTransition(R.id.transition_start)
        binding.motionLayout.setTransitionDuration(Constant.DURATION)
        binding.motionLayout.transitionToEnd()
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
                    ContextCompat.getColor(context, R.color.purple_700_25)
                )
            }

            if (it.hasValue(R.styleable.LoadingButton_lb_default_text)) {
                defaultText = it.getString(R.styleable.LoadingButton_lb_default_text).toString()
            }

            typedArray.recycle()
        }

        binding.customTextView.text = defaultText
        binding.view.setBackgroundColor(backgroundColor!!)
    }

    fun animateToEnd() {
        binding.motionLayout.progress = 1f
    }

}