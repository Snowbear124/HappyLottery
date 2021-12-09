package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button

class DataActivity : AppCompatActivity() {
//    private val REQUEST_RECODE_1 = 1
//    private val REQUEST_RECODE_2 = 2
//    private val REQUEST_RECODE_3 = 3
//    private val REQUEST_RECODE_4 = 4
    val TAG = DataActivity::class.java.simpleName
    val intentActivity = Intent()
    var dataNameMap = mutableMapOf<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        val data_1 = findViewById<Button>(R.id.data_1)  // 一定要寫在fun內，不然會崩潰
        val data_2 = findViewById<Button>(R.id.data_2)
        val data_3 = findViewById<Button>(R.id.data_3)
        val data_4 = findViewById<Button>(R.id.data_4)
        val set_1 = findViewById<Button>(R.id.set_1)
        val set_2 = findViewById<Button>(R.id.set_2)
        val set_3 = findViewById<Button>(R.id.set_3)
        val set_4 = findViewById<Button>(R.id.set_4)
        val but_back = findViewById<Button>(R.id.but_back)

        data_1.setOnTouchListener(GlobalVariable().butAction)
        data_2.setOnTouchListener(GlobalVariable().butAction)
        data_3.setOnTouchListener(GlobalVariable().butAction)
        data_4.setOnTouchListener(GlobalVariable().butAction)
        set_1.setOnTouchListener(GlobalVariable().butAction)
        set_2.setOnTouchListener(GlobalVariable().butAction)
        set_3.setOnTouchListener(GlobalVariable().butAction)
        set_4.setOnTouchListener(GlobalVariable().butAction)
        but_back.setOnTouchListener(GlobalVariable().butAction)

        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)
//        val data_state = shareLogin.getInt("data_state", 0)
        val inteDataItemSet = intentActivity.setClass(this, DataItemSet::class.java)

        val dataName_1 = shareData_1.getString("dataName", "DATA 1")
        val dataName_2 = shareData_2.getString("dataName", "DATA 2")
        val dataName_3 = shareData_3.getString("dataName", "DATA 3")
        val dataName_4 = shareData_4.getString("dataName", "DATA 4")

        data_1.setText(dataName_1)
        data_2.setText(dataName_2)
        data_3.setText(dataName_3)
        data_4.setText(dataName_4)

        data_1.setOnClickListener {
            shareLogin.edit().putInt("data_state", 1).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            backUp()
//            finish()
        }

        data_2.setOnClickListener {
            shareLogin.edit().putInt("data_state", 2).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            backUp()
//            finish()
        }

        data_3.setOnClickListener {
            shareLogin.edit().putInt("data_state", 3).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            backUp()
//            finish()
        }

        data_4.setOnClickListener {
            shareLogin.edit().putInt("data_state", 4).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            backUp()
//            finish()
        }

        set_1.setOnClickListener {
            shareLogin.edit().putInt("data_state", 1).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(inteDataItemSet)
        }

        set_2.setOnClickListener {
            shareLogin.edit().putInt("data_state", 2).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(inteDataItemSet)
        }

        set_3.setOnClickListener {
            shareLogin.edit().putInt("data_state", 3).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(inteDataItemSet)
        }

        set_4.setOnClickListener {
            shareLogin.edit().putInt("data_state", 4).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(inteDataItemSet)
        }

        but_back.setOnClickListener {
            backUp()
//            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
//        Log.d(TAG, "onRestart: ")

        val data_1 = findViewById<Button>(R.id.data_1)  // 一定要寫在fun內，不然會崩潰
        val data_2 = findViewById<Button>(R.id.data_2)
        val data_3 = findViewById<Button>(R.id.data_3)
        val data_4 = findViewById<Button>(R.id.data_4)

        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)

        val dataName_1 = shareData_1.getString("dataName", "DATA 1")
        val dataName_2 = shareData_2.getString("dataName", "DATA 2")
        val dataName_3 = shareData_3.getString("dataName", "DATA 3")
        val dataName_4 = shareData_4.getString("dataName", "DATA 4")

        data_1.setText(dataName_1)
        data_2.setText(dataName_2)
        data_3.setText(dataName_3)
        data_4.setText(dataName_4)
    }

    fun backUp() {
        val intentMainActivity = intentActivity.setClass(this, MainActivity::class.java)
        startActivity(intentMainActivity)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            backUp()
            return false
        }
        return super.onKeyDown(keyCode, event)
    }

    //用ctrl+o開啟
    //intent接收回傳資料使用，讓另一個Atcivity關閉後還能回傳資料
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val data_1 = findViewById<Button>(R.id.data_1)
//        val data_2 = findViewById<Button>(R.id.data_2)
//        val data_3 = findViewById<Button>(R.id.data_3)
//        val data_4 = findViewById<Button>(R.id.data_4)
//        var dataName: String? = ""
//
//        if(resultCode == Activity.RESULT_OK){
//            if(requestCode == REQUEST_RECODE_1) {
//                dataName = data?.getStringExtra("DATANAME")
//                data_1.setText(dataName.toString())
//                Log.d(TAG, "Data 1 text become ${dataName.toString()}")
//
//            }else if(requestCode == REQUEST_RECODE_2) {
//                dataName = data?.getStringExtra("DATANAME")
//                data_2.setText(dataName.toString())
//                Log.d(TAG, "Data 2 text become ${dataName.toString()}")
//
//            }else if(requestCode == REQUEST_RECODE_3) {
//                dataName = data?.getStringExtra("DATANAME")
//                data_3.setText(dataName.toString())
//                Log.d(TAG, "Data 3 text become ${dataName.toString()}")
//
//            }else if(requestCode == REQUEST_RECODE_4) {
//                dataName = data?.getStringExtra("DATANAME")
//                data_4.setText(dataName.toString())
//                Log.d(TAG, "Data 3 text become ${dataName.toString()}")
//            }
//        }
//    }

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