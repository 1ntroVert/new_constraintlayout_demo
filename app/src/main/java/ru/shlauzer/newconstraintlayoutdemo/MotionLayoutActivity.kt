package ru.shlauzer.newconstraintlayoutdemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_motion_layout.*
import kotlinx.android.synthetic.main.activity_motion_layout.title as film_title

class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout)

        description.movementMethod = ScrollingMovementMethod()

        val clickAction: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (motion_layout.currentState in setOf(
                        R.id.film_01_details,
                        R.id.film_02_details
                    )
                ) return

                val startState = motion_layout.currentState
                val endState =
                    if (motion_layout.currentState == R.id.film_01_preview) R.id.film_01_details else R.id.film_02_details

                motion_layout.setTransition(startState, endState)
                motion_layout.transitionToEnd()
            }
        }

        category.setOnClickListener(clickAction)
        film_title.setOnClickListener(clickAction)
        divider.setOnClickListener(clickAction)
    }

    override fun onBackPressed() {
        if (motion_layout.currentState in setOf(R.id.film_01_details,  R.id.film_02_details)) {
            motion_layout.transitionToStart()

            motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                }

                override fun onTransitionChange(
                    p0: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {

                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    if (currentId == R.id.film_01_preview || motion_layout.currentState == R.id.film_02_preview) {
                        motion_layout.setTransitionListener(null)
                        motion_layout.setTransition(
                            motion_layout.currentState,
                            if (motion_layout.currentState == R.id.film_01_preview) R.id.film_02_preview else R.id.film_01_preview
                        )
                    }
                }
            })
        } else {
            super.onBackPressed()
        }
    }
}