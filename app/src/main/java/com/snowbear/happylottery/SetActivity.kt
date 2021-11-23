package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class SetActivity : AppCompatActivity() {
    val TAG = SetActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set)
        Log.d(TAG, "onCreate: ")

        val intentActivity = Intent()
        val layoutInflater = LayoutInflater.from(this)
        val set_data = findViewById<Button>(R.id.set_data)
        val set_back = findViewById<Button>(R.id.set_back)
        val set_language = findViewById<Button>(R.id.set_language)
        val set_exit = findViewById<Button>(R.id.set_exit)

        set_data.setOnTouchListener(butAction)
        set_language.setOnTouchListener(butAction)
        set_exit.setOnTouchListener(butAction)

        set_data.setOnClickListener {
            val intentDataActivity = intentActivity.setClass(this, DataActivity::class.java)
            startActivity(intentDataActivity)
        }

        set_exit.setOnClickListener {
        }

    }

    fun backUp(view: View) {
        finish() //關閉Activity介面
    }

    //按鈕按壓時的大小變化
    val butAction = object: View.OnTouchListener{
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when(event?.action) {
                MotionEvent.ACTION_DOWN ->
                    v?.animate()?.scaleX(0.85f)?.scaleY(0.85f)?.setDuration(150)?.start()
                MotionEvent.ACTION_UP ->
                    v?.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(150)?.start()
            }
            return false
        }
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
}

