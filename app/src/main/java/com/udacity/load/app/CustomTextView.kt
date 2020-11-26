package com.udacity.load.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Align
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var textPaint: TextPaint? = null

    var text: String? = "Woz!"
        set(value) {
            field = value
            invalidate()
        }

    init {
        textPaint = TextPaint()
        textPaint!!.isAntiAlias = true
        textPaint!!.textSize = 16 * resources.displayMetrics.density
        textPaint!!.color = Color.BLACK
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

    private fun getTextHeight(text: String, paint: Paint): Float {
        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        return rect.height().toFloat()
    }

    private fun drawCenter(canvas: Canvas, text: String) {
        val textPaint = TextPaint()
        textPaint.textAlign = Align.CENTER
        textPaint.textSize = 20 * resources.displayMetrics.density
        textPaint.color = ContextCompat.getColor(context, R.color.white)

        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2).toInt()
        canvas.drawText(text, xPos.toFloat(), yPos.toFloat(), textPaint)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCenter(canvas, "Woz!")
    }
}