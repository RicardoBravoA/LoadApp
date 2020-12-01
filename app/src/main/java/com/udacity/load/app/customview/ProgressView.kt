package com.udacity.load.app.customview

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.udacity.load.app.R
import com.udacity.load.app.util.Constant

class ProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var duration = Constant.DURATION
    private var progressColor = ContextCompat.getColor(context, R.color.purple_700_50)
    private var oldProgress = 0
    private var currentProgress = 0

    private var progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var progressRectF = RectF()

    init {
        initUI(attrs)
    }

    private fun initUI(attrs: AttributeSet?) {
        setWillNotDraw(false)

        attrs?.let {
            val array: TypedArray =
                context.obtainStyledAttributes(it, R.styleable.ProgressView)
            if (array.length() > 0) {
                progressColor = array.getColor(
                    R.styleable.ProgressView_pv_progressColor,
                    ContextCompat.getColor(context, R.color.purple_700_50)
                )

                val progress =
                    array.getInt(R.styleable.ProgressView_pv_progress, currentProgress)
                setProgress(progress)
            }
            array.recycle()
        }
        initPaint()
    }

    private fun initPaint() {
        progressPaint.apply {
            style = Paint.Style.FILL
            color = progressColor
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        progressRectF = RectF(0f, 0f, w.toFloat(), h.toFloat())
        progressRectF.bottom = h.toFloat()
        updateRect(progressRectF)

    }

    private fun updateRect(rectF: RectF) {
        rectF.right = (width * currentProgress / 100).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawRect(progressRectF, progressPaint)
        }
        super.onDraw(canvas)
    }

    fun setProgress(inputProgress: Int, animated: Boolean = true) {
        if (animated) {
            val animator = ValueAnimator.ofInt(oldProgress, inputProgress)
            animator.interpolator = AccelerateDecelerateInterpolator()
            animator.addUpdateListener {
                currentProgress = it.animatedValue as Int
                updateRect(progressRectF)
                ViewCompat.postInvalidateOnAnimation(this)
            }
            animator.duration = duration
            animator.start()
        } else {
            currentProgress = inputProgress
            updateRect(progressRectF)
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    fun setProgressColor(color: Int) {
        progressColor = color
        initPaint()
    }

}
