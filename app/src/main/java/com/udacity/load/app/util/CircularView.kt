package com.udacity.load.app.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.udacity.load.app.R

class CircularView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint: Paint? = null
    private var rect: RectF? = null
    private var progress = 0f

    @ColorInt
    private var backgroundColor: Int? = null

    init {
        backgroundColor = ContextCompat.getColor(context, R.color.purple_700)
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
                ContextCompat.getColor(context, R.color.purple_700)
            )
            paint?.color = backgroundColor!!
            it.recycle()
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val left = 0
        val width = width
        val top = 0
        rect!![left.toFloat(), top.toFloat(), left + width.toFloat()] = top + width.toFloat()

        if (progress != 0f) {
            canvas.drawArc(rect!!, 0f, 360 * progress, true, paint!!)
        }
    }

    fun setProgress(percentage: Float) {
        this.progress = percentage / 100
        invalidate()
    }

}