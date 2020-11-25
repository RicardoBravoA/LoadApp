package com.udacity.load.app.util.motion

import androidx.constraintlayout.motion.widget.MotionLayout

class CustomTransitionListener(private val customMotionListener: CustomMotionListener) :
    MotionLayout.TransitionListener {
    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        //Do nothing
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        //Do nothing
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, p1: Int) {
        customMotionListener.onTransitionCompleted(motionLayout)
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
        //Do nothing
    }

}