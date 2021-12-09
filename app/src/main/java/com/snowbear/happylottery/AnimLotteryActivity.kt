package com.snowbear.happylottery

import android.app.Activity
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.ViewAnimator

class AnimLotteryActivity : AppCompatActivity() {
    val TAG = AnimLotteryActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_lottery)

    }

    override fun onStart() {
        super.onStart()

        val anim_lottery = findViewById<ImageView>(R.id.anim_lottery)
        anim_lottery.setImageResource(R.drawable.avd_lottrey_balls)
        val animLottery = anim_lottery.drawable as AnimatedVectorDrawable
        animLottery.start()
        timer()
    }

    fun timer() {
        object: CountDownTimer(2600, 1000) {
            override fun onTick(mills: Long) {
                Log.d(TAG, "onTick: " + mills)
            }

            override fun onFinish() {
                Log.d(TAG, "Finish!")
                finish()
            }
        }.start()
    }
}