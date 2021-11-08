package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    //也可以直接用"MainActivity"字串，但用class這個方式不占記憶體空間，會比較好
    val TAG = MainActivity::class.java.simpleName
    val testList = listOf<String>("Apply","Banana","Cherry","Dragonfruit","Fig")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.activity_data)
    }

    fun lottery(view: View) {
        val item = testList.random()
//        println("Item: $item")  //系統內部輸出的值
        Log.d(TAG,"Lottery Item = $item")    //標籤，這裡做debug用
    }

    fun setActivity(view: View) {
        // intent是用來做介面傳送功能的程式
        val intentSetActivity = Intent(this, SetActivity::class.java)
        startActivity(intentSetActivity)    // startActivity指開啟()內的介面
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