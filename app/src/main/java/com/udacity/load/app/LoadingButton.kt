package com.udacity.load.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import com.udacity.load.app.databinding.LoadingButtonBinding
import com.udacity.load.app.util.Constant

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr) {

    private var binding: LoadingButtonBinding =
        LoadingButtonBinding.inflate(LayoutInflater.from(context), this, true)

    @ColorInt
    private var backgroundColor: Int? = null

    init {
        backgroundColor = ContextCompat.getColor(context, R.color.purple_700_25)
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
            backgroundColor = typedArray.getColor(
                R.styleable.LoadingButton_lb_background,
                ContextCompat.getColor(context, R.color.purple_700_25)
            )
            binding.view.setBackgroundColor(backgroundColor!!)

            typedArray.recycle()
        }


//        binding.customTextView.text = "Woz!"
    }

    fun animateToEnd() {
        binding.motionLayout.progress = 1f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}