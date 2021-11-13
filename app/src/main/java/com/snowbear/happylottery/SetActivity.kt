package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog

class SetActivity : AppCompatActivity() {
    val TAG = SetActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set)
        Log.d(TAG, "onCreate: ")
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

    fun backUp(view: View) {
        finish() //關閉Activity介面
    }

    fun exit(view: View) {
        val exitMessage = "Do you want to exit?"
        AlertDialog.Builder(this)
            .setMessage(exitMessage)
            .setPositiveButton("Yes", null) //右邊的按鈕(文字, 功能)
            .setNeutralButton("No", null)   //左邊的按鈕(文字, 功能)
            .show()
    }
}

