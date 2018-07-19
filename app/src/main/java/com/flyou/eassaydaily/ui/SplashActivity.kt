package com.flyou.eassaydaily.ui

import android.animation.Animator
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.animation.AnticipateOvershootInterpolator
import com.flyou.eassaydaily.R
import kotlinx.android.synthetic.main.activity_splash.animation_view
import kotlinx.android.synthetic.main.activity_splash.info


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        val face = Typeface.createFromAsset(assets,
                "fonts/font1.ttf")
        info.typeface = face

        animation_view.addAnimatorListener(myAnimatorListener)
    }


    private val myAnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animator: Animator) {

            info.animate().translationY(-330f).setInterpolator(AnticipateOvershootInterpolator(1.0f)).duration= 1000
        }

        override fun onAnimationEnd(animator: Animator) {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        override fun onAnimationCancel(animator: Animator) {

        }

        override fun onAnimationRepeat(animator: Animator) {

        }
    }


}
