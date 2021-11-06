package com.snowbear.happylottery

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
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.set)
    }

    fun lottery(view: View) {
        val item = testList.random()
//        println("Item: $item")  //系統內部輸出的值
        Log.d(TAG,"Lottery Item = $item")    //標籤，這裡做debug用
    }
}