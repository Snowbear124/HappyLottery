package com.snowbear.happylottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class SetActivity : AppCompatActivity() {
    val TAG = SetActivity::class.java.simpleName
    var backFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set)

        val intentActivity = Intent()
        val set_data = findViewById<Button>(R.id.set_data)
        val set_back = findViewById<Button>(R.id.set_back)
        val set_language = findViewById<Button>(R.id.set_language)
        val set_exit = findViewById<Button>(R.id.set_exit)
        val set_switch = findViewById<Switch>(R.id.switch_item_re)

        butAnim()
        switchAnimationButtoin()

        set_data.setOnClickListener {
            touchDataButton()
        }

        set_exit.setOnClickListener {
            isExit()
        }

        set_back.setOnClickListener{
            backAnim()
        }

    }

    private fun touchDataButton() {
        val DataActivity = Intent(this, DataActivity::class.java)
        startActivity(DataActivity)
        overridePendingTransition(R.anim.fade_in, R.anim.no_anim_transition)
    }

    private fun switchAnimationButtoin() {
        val lottery_anim = findViewById<Switch>(R.id.switch_anim)
        val sharedLogin = getSharedPreferences(GlobalVariable.login, MODE_PRIVATE)
        val switch_anim = sharedLogin.getBoolean(GlobalVariable.anim_switch, true)

        lottery_anim.isChecked = switch_anim

        //compoundButton -> butView, b -> boolean
        return lottery_anim.setOnCheckedChangeListener { compoundButton, b ->
            if(b) Toast.makeText(this, getString(R.string.open_anim), Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, getString(R.string.close_anim), Toast.LENGTH_SHORT).show()

            sharedLogin
                .edit()
                .putBoolean(GlobalVariable.anim_switch, b)
                .apply()
        }
    }

    private fun  switchTest() {
        val lottery_anim = findViewById<Switch>(R.id.switch_anim)
        lottery_anim.isChecked = true

        //compoundButton -> butView, b -> boolean
        return lottery_anim.setOnCheckedChangeListener { compoundButton, b ->
            //Set switch action
        }
    }

    private fun butAnim() {
        val set_data = findViewById<Button>(R.id.set_data)
        val set_back = findViewById<Button>(R.id.set_back)
        val set_language = findViewById<Button>(R.id.set_language)
        val set_exit = findViewById<Button>(R.id.set_exit)
        val anim = GlobalVariable().butAction

        set_data.setOnTouchListener(anim)
        set_language.setOnTouchListener(anim)
        set_exit.setOnTouchListener(anim)
        set_back.setOnTouchListener(anim)
    }

    private fun isExit() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_exit, null)
        val but_yes = view.findViewById<Button>(R.id.but_yes)
        val but_no = view.findViewById<Button>(R.id.but_no)

        val bundle = AlertDialog.Builder(this).setView(view)
        val dialog = bundle.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val anim = GlobalVariable().butAction
        but_yes.setOnTouchListener(anim)
        but_no.setOnTouchListener(anim)

        but_yes.setOnClickListener {
            App.addActivity(this)   //將此activity加入list
            App.clearActivity() //關閉所有的activity
        }

        but_no.setOnClickListener {
            dialog.dismiss() //返回
        }
//
    }

    private fun backAnim() {
        finish()
        overridePendingTransition(R.anim.no_anim_transition, R.anim.slide_up_out)
    }



    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backAnim()
            return false
        }
        return super.onKeyDown(keyCode, event)
    }

    //Activity週期變化顯示---------------------------------------------------
//override fun onStart() {
//    super.onStart()
//    Log.d(TAG, "onStart: ")
//}

//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume: ")
//    }

//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause: ")
//    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        if (backFlag == true) {
//            finish()
        }
    }

//    override fun onRestart() {
//        super.onRestart()
//        Log.d(TAG, "onRestart: ")
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
//        if (backFlag == false) {
//            System.exit(0)  //確保程式完全退出
//        }
    }
}

