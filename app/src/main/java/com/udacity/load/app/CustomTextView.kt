package com.udacity.load.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var text: String? = "Woz!"
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

    private fun drawCenter(canvas: Canvas, text: String) {
        /*val textPaint = TextPaint()
        textPaint.textAlign = Align.CENTER
        textPaint.textSize = 20 * resources.displayMetrics.density
        textPaint.color = Color.WHITE

        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2).toInt()
        canvas.drawText(text, xPos.toFloat(), yPos.toFloat(), textPaint)*/

        val textPaint = Paint()
        textPaint.textAlign = Paint.Align.CENTER

        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2)
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.

        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        canvas.drawText("Hello", xPos.toFloat(), yPos.toFloat(), textPaint)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCenter(canvas, "Woz!")
    }
}