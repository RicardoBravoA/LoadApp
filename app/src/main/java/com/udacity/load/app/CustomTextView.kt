package com.udacity.load.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var text: String? = null
        set(value) {
            field = value
            invalidate()
        }

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTextView, 0, 0
        )

        typedArray.let {
            text = typedArray.getString(
                R.styleable.CustomTextView_ctv_text
            )
            typedArray.recycle()
        }
    }

    private fun drawCenter(canvas: Canvas) {
        val textPaint = TextPaint()
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 20 * resources.displayMetrics.density
        textPaint.color = Color.WHITE

        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2)
        text?.let { canvas.drawText(it, xPos.toFloat(), yPos, textPaint) }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCenter(canvas)
    }
}