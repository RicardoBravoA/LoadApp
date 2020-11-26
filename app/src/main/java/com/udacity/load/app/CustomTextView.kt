package com.udacity.load.app

import android.content.Context
import android.graphics.Canvas
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var mText = "This is some text."
    var mTextPaint: TextPaint? = null
    var mStaticLayout: StaticLayout? = null

    init {
        mTextPaint = TextPaint()
        mTextPaint?.isAntiAlias = true
        mTextPaint?.textSize = 16 * resources.displayMetrics.density
        mTextPaint?.color = ContextCompat.getColor(context, R.color.black)

        // default to a single line of text
        val width = mTextPaint!!.measureText(mText).toInt()
        mStaticLayout = StaticLayout(
            mText, mTextPaint,
            width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, false
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width: Int
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthRequirement = MeasureSpec.getSize(widthMeasureSpec)
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement
        } else {
            width = mStaticLayout!!.width + paddingLeft + paddingRight
            if (widthMode == MeasureSpec.AT_MOST) {
                if (width > widthRequirement) {
                    width = widthRequirement
                    // too long for a single line so relayout as multiline
                    mStaticLayout = StaticLayout(
                        mText,
                        mTextPaint,
                        width,
                        Layout.Alignment.ALIGN_NORMAL,
                        1.0f,
                        0f,
                        false
                    )
                }
            }
        }

        var height: Int
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightRequirement = MeasureSpec.getSize(heightMeasureSpec)
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement
        } else {
            height = mStaticLayout!!.height + paddingTop + paddingBottom
            if (heightMode == MeasureSpec.AT_MOST) {
                height = height.coerceAtMost(heightRequirement)
            }
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        mStaticLayout!!.draw(canvas)
        canvas.restore()
    }
}