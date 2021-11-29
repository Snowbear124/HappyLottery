package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    //也可以直接用"MainActivity"字串，但用class這個方式不占記憶體空間，會比較好
    val TAG = MainActivity::class.java.simpleName
    val intentMainActivity = Intent()
    val testList = listOf<String>("Apply","Banana","Cherry","Dragonfruit","Fig")
    val itemList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)

        val but_dataName = findViewById<Button>(R.id.data_name)
        val but_start = findViewById<Button>(R.id.but_start)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_set = findViewById<Button>(R.id.but_set)
        val intentMainActivity = Intent()

        but_dataName.setOnTouchListener(butAction)
        but_lottery.setOnTouchListener(butAction)
        but_set.setOnTouchListener(butAction)
        but_start.setOnTouchListener(butAction)

        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)

        val data_state: Int = shareLogin.getInt("data_state", 1)
        Log.d(TAG, "data_state: $data_state")

        if(data_state == 1) {
            val dataName = shareData_1.getString("dataName", "DATA 1")
            but_dataName.setText(dataName)

        }else if(data_state == 2) {
            val dataName = shareData_2.getString("dataName", "DATA 2")
            but_dataName.setText(dataName)

        }else if(data_state == 3) {
            val dataName = shareData_3.getString("dataName", "DATA 3")
            but_dataName.setText(dataName)

        }else if(data_state == 4) {
            val dataName = shareData_4.getString("dataName", "DATA 4")
            but_dataName.setText(dataName)

        }else {
            but_dataName.setText("No find data")
        }

        but_dataName.setOnClickListener{
            intentMainActivity.setClass(this, DataActivity::class.java)
            startActivity(intentMainActivity)
        }

        val viewLottery = LayoutInflater.from(this).inflate(R.layout.dialog_lottery, null, false)
        val layout = findViewById<ConstraintLayout>(R.id.main_layout)

        but_lottery.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val dialog = alertDialog.show()
//            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }
    }

    override fun onRestart() {
        super.onRestart()
//        Log.d(TAG, "onRestart: ")

        val but_dataName = findViewById<Button>(R.id.data_name)
        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)

        val data_state = shareLogin.getInt("data_state", 1)
        Log.d(TAG, "Data state: ${shareLogin.getInt("data_state", 1)}")

        if(data_state == 1) {
            val dataName = shareData_1.getString("dataName", "DATA 1")
            but_dataName.setText(dataName)

        }else if(data_state == 2) {
            val dataName = shareData_2.getString("dataName", "DATA 2")
            but_dataName.setText(dataName)

        }else if(data_state == 3) {
            val dataName = shareData_3.getString("dataName", "DATA 3")
            but_dataName.setText(dataName)

        }else if(data_state == 4) {
            val dataName = shareData_4.getString("dataName", "DATA 4")
            but_dataName.setText(dataName)

        }else {
            but_dataName.setText("No find data")
        }
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

    fun lottery(view: View) {
//        intentMainActivity.setClass()

//        val item = testList.random()
//        Log.d(TAG,"Lottery Item = $item")    //標籤，這裡做debug用
    }

    fun setActivity(view: View) {
        // intent是用來做介面傳送功能的程式
        val intentSetActivity = Intent(this, SetActivity::class.java)
        startActivity(intentSetActivity)    // startActivity指開啟()內的介面
    }

//Activity週期變化顯示---------------------------------------------------
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart: ")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume: ")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause: ")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop: ")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy: ")
//    }
//--------------------------------------------------------------
}