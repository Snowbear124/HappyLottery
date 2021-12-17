package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.util.Log
import android.view.Window
import android.widget.Button

class DataActivity : AppCompatActivity() {
    val TAG = DataActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        val but_back = findViewById<Button>(R.id.but_back)

        butAnim()
        getSharedPreData()
        touchDataButton()
        touchDataSetButton()

        but_back.setOnClickListener {
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
//        Log.d(TAG, "onRestart: ")
        getSharedPreData()
    }

    private fun butAnim() {
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
    }

    private fun touchDataButton() {
        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val data_1 = findViewById<Button>(R.id.data_1)  // 一定要寫在fun內，不然會崩潰
        val data_2 = findViewById<Button>(R.id.data_2)
        val data_3 = findViewById<Button>(R.id.data_3)
        val data_4 = findViewById<Button>(R.id.data_4)

        data_1.setOnClickListener {
            shareLogin.edit().putInt("data_state", 1).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            finish()
        }

        data_2.setOnClickListener {
            shareLogin.edit().putInt("data_state", 2).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            finish()
        }

        data_3.setOnClickListener {
            shareLogin.edit().putInt("data_state", 3).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            finish()
        }

        data_4.setOnClickListener {
            shareLogin.edit().putInt("data_state", 4).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            finish()
        }
    }

    private fun touchDataSetButton() {
        val DataItemSet = Intent(this, DataItemSet::class.java)
        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val set_1 = findViewById<Button>(R.id.set_1)
        val set_2 = findViewById<Button>(R.id.set_2)
        val set_3 = findViewById<Button>(R.id.set_3)
        val set_4 = findViewById<Button>(R.id.set_4)

        set_1.setOnClickListener {
            shareLogin.edit().putInt("data_state", 1).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            enterAnim()
        }

        set_2.setOnClickListener {
            shareLogin.edit().putInt("data_state", 2).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            enterAnim()
        }

        set_3.setOnClickListener {
            shareLogin.edit().putInt("data_state", 3).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            enterAnim()
        }

        set_4.setOnClickListener {
            shareLogin.edit().putInt("data_state", 4).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            enterAnim()
        }
    }

    private fun getSharedPreData() {
        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)

        val DATA_1 = getString(R.string.data_1)
        val DATA_2 = getString(R.string.data_2)
        val DATA_3 = getString(R.string.data_3)
        val DATA_4 = getString(R.string.data_4)

        val dataName_1 = shareData_1.getString("dataName", DATA_1)
        val dataName_2 = shareData_2.getString("dataName", DATA_2)
        val dataName_3 = shareData_3.getString("dataName", DATA_3)
        val dataName_4 = shareData_4.getString("dataName", DATA_4)

        val data_1 = findViewById<Button>(R.id.data_1)
        val data_2 = findViewById<Button>(R.id.data_2)
        val data_3 = findViewById<Button>(R.id.data_3)
        val data_4 = findViewById<Button>(R.id.data_4)

        data_1.text = dataName_1
        data_2.text = dataName_2
        data_3.text = dataName_3
        data_4.text = dataName_4

    }

    private fun enterAnim() {
        overridePendingTransition(R.anim.fade_in, R.anim.no_anim_transition)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_anim_transition, R.anim.fade_out)
    }
}