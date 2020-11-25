package com.udacity.load.app.util

import android.util.Log
import android.view.animation.Animation
import android.view.animation.Transformation

class CircularViewAnimation(
    private val circularView: CircularView,
    progress: Float
) :
    Animation() {

    init {
        Log.i("z- circle", "true")
    }

    private var oldAngle: Float = circularView.angle
    private var newAngle = (progress * 360) / 100

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        val angle: Float = 0 + ((newAngle - oldAngle) * interpolatedTime)

        circularView.angle = angle
        circularView.requestLayout()
    }

}