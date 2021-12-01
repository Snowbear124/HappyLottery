package com.snowbear.happylottery

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AnimLotteryActivity : AppCompatActivity() {
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
    }
}