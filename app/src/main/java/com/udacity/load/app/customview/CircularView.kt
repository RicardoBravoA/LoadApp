package com.udacity.load.app.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.udacity.load.app.R
import com.udacity.load.app.util.Constant

class CircularView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint: Paint? = null
    private var rect: RectF? = null
    private var animator: ValueAnimator? = null
    var angle = 0

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect?.set(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        paint?.color = backgroundColor!!
        rect?.let {
            canvas.drawArc(
                it,
                START_ANGLE,
                angle.toFloat(),
                true,
                paint!!
            )
        }
    }

    fun show(timeDuration: Long = Constant.DURATION) {
        animator?.cancel()
        animator = ValueAnimator.ofInt(0, 360).apply {
            duration = timeDuration
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                angle = valueAnimator.animatedValue as Int
                invalidate()
            }
        }
        animator?.start()
    }

    companion object {
        private const val START_ANGLE = 360f
    }
}