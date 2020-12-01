package com.udacity.load.app.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.udacity.load.app.R
import com.udacity.load.app.util.Constant

class CircularView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint: Paint? = null
    private var rect: RectF? = null
    var angle = 0f

    @ColorInt
    private var backgroundColor: Int? = null

    init {
        backgroundColor = ContextCompat.getColor(context, R.color.teal_700)
        paint = Paint()
        paint?.color = backgroundColor as Int
        paint?.isAntiAlias = true
        paint?.style = Paint.Style.FILL
        rect = RectF()
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.CircularView
        )

        typedArray.let {
            backgroundColor = typedArray.getColor(
                R.styleable.CircularView_cv_color,
                ContextCompat.getColor(context, R.color.teal_700)
            )
            paint?.color = backgroundColor!!
            typedArray.recycle()
        }

    }

    override fun onDraw(canvas: Canvas) {
        val left = 0
        val width = width
        val top = 0
        rect!![left.toFloat(), top.toFloat(), left + width.toFloat()] = top + width.toFloat()

        canvas.drawArc(rect!!, 0f, angle, true, paint!!)
        super.onDraw(canvas)
    }

    fun progress(progress: Float, duration: Long = Constant.DURATION) {
        // restart animation
        if (angle != 0f) {
            angle = 0f
            val circleAnimation = CircularViewAnimation(this, 0f)
            circleAnimation.duration = duration
            startAnimation(circleAnimation)
        }
        val circleAnimation = CircularViewAnimation(this, progress)
        circleAnimation.duration = duration
        startAnimation(circleAnimation)
    }
}