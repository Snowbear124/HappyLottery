package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class LotteryResultActivity : AppCompatActivity() {
    val TAG = LotteryResultActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottery_result)
    }
//Activity週期變化顯示---------------------------------------------------
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
//--------------------------------------------------------------

    fun lottery(view: View) {

    }

    fun setActivity(view: View) {
        // intent是用來做介面傳送功能的程式
        val intentSetActivity = Intent(this, SetActivity::class.java)
        startActivity(intentSetActivity)    // startActivity指開啟()內的介面
    }

    fun ballColor(num : Int) {
        val butLottery = findViewById<Button>(R.id.but_lottery)
        if (num%4 == 0) {
            butLottery.setBackgroundColor(R.drawable.lotteryball_yellow)
        }else if (num%4 == 3) {
            butLottery.setBackgroundColor(R.drawable.lotteryball_rad)
        }else if (num%4 == 2) {
            butLottery.setBackgroundColor(R.drawable.lotteryball_blue)
        }else if (num%4 == 1) {
            butLottery.setBackgroundColor(R.drawable.lotteryball_green)
        }
    }
}