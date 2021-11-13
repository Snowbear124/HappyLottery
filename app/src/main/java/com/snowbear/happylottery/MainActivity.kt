package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    //也可以直接用"MainActivity"字串，但用class這個方式不占記憶體空間，會比較好
    val TAG = MainActivity::class.java.simpleName
    val intentMainActivity = Intent()
    val testList = listOf<String>("Apply","Banana","Cherry","Dragonfruit","Fig")
    var butTouchSize = 0.9f
    var butUpSize = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.set)
//        setContentView(R.layout.test)

        val dataName = findViewById<Button>(R.id.data_name)
        val intentDataActivity = Intent(this, DataActivity::class.java)
        dataName.setOnClickListener{
            startActivity(intentDataActivity)
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

    fun lottery(view: View) {
//        intentMainActivity.setClass()

        val item = testList.random()
        Log.d(TAG,"Lottery Item = $item")    //標籤，這裡做debug用

        //按鈕按壓時的大小變化(測試中，目前地一下不會有動作)
//        view.setOnTouchListener { _, event ->
//            if (event.action == MotionEvent.ACTION_DOWN)
//                view.animate().scaleX(butTouchSize).scaleY(butTouchSize).setDuration(150).start()
//            if (event.action == MotionEvent.ACTION_UP)
//                view.animate().scaleX(butUpSize).scaleY(butUpSize).setDuration(150).start()
//            false
//            //回傳false表示傳送完一次資料後，就重製，使按鈕可以一直傳送資料；若是用true，表示按鈕只傳用一次就完成工作
//        }
    }

    fun setActivity(view: View) {
        // intent是用來做介面傳送功能的程式
        val intentSetActivity = Intent(this, SetActivity::class.java)
        startActivity(intentSetActivity)    // startActivity指開啟()內的介面
    }

}