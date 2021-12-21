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
        winTransition()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        butAnim()
        getSharedPreData()
        touchDataButton()
        touchDataSetButton()
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
        val data_5 = findViewById<Button>(R.id.data_5)
        val data_6 = findViewById<Button>(R.id.data_6)
        val set_1 = findViewById<Button>(R.id.set_1)
        val set_2 = findViewById<Button>(R.id.set_2)
        val set_3 = findViewById<Button>(R.id.set_3)
        val set_4 = findViewById<Button>(R.id.set_4)
        val set_5 = findViewById<Button>(R.id.set_5)
        val set_6 = findViewById<Button>(R.id.set_6)

        data_1.setOnTouchListener(GlobalVariable().butAction)
        data_2.setOnTouchListener(GlobalVariable().butAction)
        data_3.setOnTouchListener(GlobalVariable().butAction)
        data_4.setOnTouchListener(GlobalVariable().butAction)
        data_5.setOnTouchListener(GlobalVariable().butAction)
        data_6.setOnTouchListener(GlobalVariable().butAction)
        set_1.setOnTouchListener(GlobalVariable().butAction)
        set_2.setOnTouchListener(GlobalVariable().butAction)
        set_3.setOnTouchListener(GlobalVariable().butAction)
        set_4.setOnTouchListener(GlobalVariable().butAction)
        set_5.setOnTouchListener(GlobalVariable().butAction)
        set_6.setOnTouchListener(GlobalVariable().butAction)
    }

    private fun touchDataButton() {
        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val data_1 = findViewById<Button>(R.id.data_1)  // 一定要寫在fun內，不然會崩潰
        val data_2 = findViewById<Button>(R.id.data_2)
        val data_3 = findViewById<Button>(R.id.data_3)
        val data_4 = findViewById<Button>(R.id.data_4)
        val data_5 = findViewById<Button>(R.id.data_5)
        val data_6 = findViewById<Button>(R.id.data_6)

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

        data_5.setOnClickListener {
            shareLogin.edit().putInt("data_state", 5).apply()
            val data_state = shareLogin.getInt("data_state", 0)
            Log.d(TAG, "Switch data: $data_state")
            finish()
        }

        data_6.setOnClickListener {
            shareLogin.edit().putInt("data_state", 6).apply()
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
        val set_5 = findViewById<Button>(R.id.set_5)
        val set_6 = findViewById<Button>(R.id.set_6)

        set_1.setOnClickListener {
            shareLogin.edit().putInt("data_state", 1).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            nextActivityAnim()
        }

        set_2.setOnClickListener {
            shareLogin.edit().putInt("data_state", 2).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            nextActivityAnim()
        }

        set_3.setOnClickListener {
            shareLogin.edit().putInt("data_state", 3).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            nextActivityAnim()
        }

        set_4.setOnClickListener {
            shareLogin.edit().putInt("data_state", 4).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            nextActivityAnim()
        }

        set_5.setOnClickListener {
            shareLogin.edit().putInt("data_state", 5).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            nextActivityAnim()
        }

        set_6.setOnClickListener {
            shareLogin.edit().putInt("data_state", 6).apply()
            Log.d(TAG, "Data_state: ${shareLogin.getInt("data_state", 0)}")
            startActivity(DataItemSet)
            nextActivityAnim()
        }
    }

    private fun getSharedPreData() {
        val shareData_1 = getSharedPreferences(GlobalVariable.data_1, MODE_PRIVATE)
        val shareData_2 = getSharedPreferences(GlobalVariable.data_2, MODE_PRIVATE)
        val shareData_3 = getSharedPreferences(GlobalVariable.data_3, MODE_PRIVATE)
        val shareData_4 = getSharedPreferences(GlobalVariable.data_4, MODE_PRIVATE)
        val shareData_5 = getSharedPreferences(GlobalVariable.data_5, MODE_PRIVATE)
        val shareData_6 = getSharedPreferences(GlobalVariable.data_6, MODE_PRIVATE)

        val DATA_1 = getString(R.string.data_1)
        val DATA_2 = getString(R.string.data_2)
        val DATA_3 = getString(R.string.data_3)
        val DATA_4 = getString(R.string.data_4)
        val DATA_5 = getString(R.string.data_5)
        val DATA_6 = getString(R.string.data_6)

        val dataName_1 = shareData_1.getString("dataName", DATA_1)
        val dataName_2 = shareData_2.getString("dataName", DATA_2)
        val dataName_3 = shareData_3.getString("dataName", DATA_3)
        val dataName_4 = shareData_4.getString("dataName", DATA_4)
        val dataName_5 = shareData_5.getString("dataName", DATA_5)
        val dataName_6 = shareData_6.getString("dataName", DATA_6)

        val data_1 = findViewById<Button>(R.id.data_1)
        val data_2 = findViewById<Button>(R.id.data_2)
        val data_3 = findViewById<Button>(R.id.data_3)
        val data_4 = findViewById<Button>(R.id.data_4)
        val data_5 = findViewById<Button>(R.id.data_5)
        val data_6 = findViewById<Button>(R.id.data_6)

        data_1.text = dataName_1
        data_2.text = dataName_2
        data_3.text = dataName_3
        data_4.text = dataName_4
        data_5.text = dataName_5
        data_6.text = dataName_6
    }

    private fun nextActivityAnim() {
        overridePendingTransition(R.anim.slide_left_in, R.anim.no_anim_transition)
    }

    private fun winTransition() {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        val explode = Explode()
        explode.duration = 600
        window.enterTransition = explode
        window.exitTransition = explode
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_anim_transition, R.anim.fade_out)
    }
}