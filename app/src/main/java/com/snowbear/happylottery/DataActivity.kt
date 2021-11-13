package com.snowbear.happylottery

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button

class DataActivity : AppCompatActivity() {
    private val REQUEST_RECODE = 100
    val TAG = DataActivity::class.java.simpleName
    val intentDataItemSet = Intent()
    var dataNameMap = mutableMapOf<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        val data_1 = findViewById<Button>(R.id.data_1)  // 一定要寫在fun內，不然會崩潰
        val data_2 = findViewById<Button>(R.id.data_2)
        val data_3 = findViewById<Button>(R.id.data_3)
        val data_4 = findViewById<Button>(R.id.data_4)

//        val dataName1 = intent.getStringExtra("DATANAME") //接收標籤為DATANAME的值，記得有型態的區別
//        data_1.setText("")

        data_1.setOnClickListener {
            dataNameMap.put(1, "Data 1")
            Log.d(TAG, "Data name 1: ${dataNameMap.get(1)}")

            intentDataItemSet.setClass(this, DataItemSet::class.java)
//            intentDataItemSet.putExtra("DATANAME", data_1.text) //將資料傳送出去("標籤", 值)
//            startActivity(intentDataItemSet)    // 開啟DataItemSet的介面
            startActivityForResult(intentDataItemSet, REQUEST_RECODE)
        }
        data_2.setOnClickListener {
            dataNameMap.put(2, "Data 2")
            Log.d(TAG, "Data name 2: ${dataNameMap.get(2)}")

            intentDataItemSet.setClass(this, DataItemSet::class.java)
            intentDataItemSet.putExtra("DATANAME", data_2.text)
            startActivity(intentDataItemSet)
        }
        data_3.setOnClickListener {
            dataNameMap.put(3, "Data 3")
            Log.d(TAG, "Data name 3: ${dataNameMap.get(3)}")

            intentDataItemSet.setClass(this, DataItemSet::class.java)
            intentDataItemSet.putExtra("DATANAME", data_3.text)
            startActivity(intentDataItemSet)
        }
        data_4.setOnClickListener {
            dataNameMap.put(4, "Data 4")
            Log.d(TAG, "Data name 4: ${dataNameMap.get(4)}")

            intentDataItemSet.setClass(this, DataItemSet::class.java)
            intentDataItemSet.putExtra("DATANAME", data_4.text)
            startActivity(intentDataItemSet)
        }
    }

    //用ctrl+o開啟
    //intent接收回傳資料使用，讓另一個Atcivity關閉後還能回傳資料
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_RECODE){
            if(resultCode == Activity.RESULT_OK) {

            }
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