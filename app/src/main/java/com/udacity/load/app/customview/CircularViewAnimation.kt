package com.udacity.load.app.customview

import android.view.animation.Animation
import android.view.animation.Transformation

class CircularViewAnimation(
    private val circularView: CircularView,
    progress: Float
) :
    Animation() {

    private var oldAngle: Float = circularView.angle
    private var newAngle = (progress * 360) / 100

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        val angle: Float = 0 + ((newAngle - oldAngle) * interpolatedTime)

        circularView.angle = angle
        circularView.requestLayout()
    }

}