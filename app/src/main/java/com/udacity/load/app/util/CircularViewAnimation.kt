package com.udacity.load.app.util

import android.view.animation.Animation
import android.view.animation.Transformation

class CircularViewAnimation(
    private val circularView: CircularView,
    private val newAngle: Float
) :
    Animation() {

    private var oldAngle: Float = circularView.angle

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
//        val newAngle = (progress * 180) / 100
        val angle: Float = 0 + ((newAngle - oldAngle) * interpolatedTime)

        circularView.angle = angle
        circularView.requestLayout()
    }

}