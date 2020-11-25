package com.udacity.load.app.util.motion

import androidx.constraintlayout.motion.widget.MotionLayout

interface CustomMotionListener {

    fun onTransitionCompleted(motionLayout: MotionLayout?)

}