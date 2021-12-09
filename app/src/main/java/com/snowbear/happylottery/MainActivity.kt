package com.snowbear.happylottery

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.transition.Slide
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    //也可以直接用"MainActivity"字串，但用class這個方式不占記憶體空間，會比較好
    val TAG = MainActivity::class.java.simpleName
    val intentMainActivity = Intent()
    var itemList = mutableListOf<String>()
    var itemCount = GlobalVariable.getItemCount()
    var exitFlag = false

//    val testList = mutableListOf<String>("Apply","Banana","Cherry","Dragonfruit","Fig")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)
        exitFlag = false
        val but_dataName = findViewById<Button>(R.id.data_name)
        val but_start = findViewById<Button>(R.id.but_start)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_set = findViewById<Button>(R.id.but_set)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)
        val tV_lottery_result = findViewById<TextView>(R.id.tV_lottery_result)
        val layout_lottery_result = findViewById<LinearLayout>(R.id.layout_lottery_result)
        val intentMainActivity = Intent()

        but_dataName.setOnTouchListener(GlobalVariable().butAction)
        but_lottery.setOnTouchListener(GlobalVariable().butAction)
        but_set.setOnTouchListener(GlobalVariable().butAction)
        but_start.setOnTouchListener(GlobalVariable().butAction)
        but_lottery_result.setOnTouchListener(GlobalVariable().butAction)

        //        val reSwitch = shareLogin.getBoolean("switch", true)

        val shareLogin = getSharedPreferences(GlobalVariable.login, MODE_PRIVATE)
        sharedPreSet(shareLogin, but_dataName)

        but_dataName.setOnClickListener{
            exitFlag = true
            intentMainActivity.setClass(this, DataActivity::class.java)
            startActivity(intentMainActivity)
        }

        val intentAnimLotteryActivity = intentMainActivity.setClass(this,AnimLotteryActivity::class.java)
        but_lottery.setOnClickListener {
            startActivity(intentAnimLotteryActivity)  //跳到抽獎動畫
        }

        but_lottery_result.setOnClickListener {
            startActivity(intentAnimLotteryActivity)  //跳到抽獎動畫
        }

        but_start.setOnClickListener {
            startActivity(intentAnimLotteryActivity)  //跳到抽獎動畫
        }

        val slide = Slide()
        but_set.setOnClickListener{
            exitFlag = true
            // intent是用來做介面傳送功能的程式
            val intentSetActivity = Intent(this, SetActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, but_set, "set_trsnsition"
            )
            startActivity(intentSetActivity, options.toBundle())    // startActivity指開啟()內的介面, 與開啟時的動畫

//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                this, but_set, ViewCompat.getTransitionName(but_set).toString())
//          ViewCompat.getTransitionName(but_set).toString() 為取得transitionName
        }
    }

    override fun onRestart() {
        super.onRestart()
//        Log.d(TAG, "onRestart: ")

        val but_dataName = findViewById<Button>(R.id.data_name)
        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        sharedPreSet(shareLogin, but_dataName)

        val layout_lottery_result = findViewById<LinearLayout>(R.id.layout_lottery_result)
        val but_start = findViewById<Button>(R.id.but_start)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val tV_lottery_result = findViewById<TextView>(R.id.tV_lottery_result)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)

        val intentAnimLotteryActivity = intentMainActivity.setClass(this,AnimLotteryActivity::class.java)
        but_lottery.setOnClickListener {
            startActivity(intentAnimLotteryActivity)  //跳到抽獎動畫
        }

        but_lottery_result.setOnClickListener {
            startActivity(intentAnimLotteryActivity)  //跳到抽獎動畫
        }

        but_start.setOnClickListener {
            startActivity(intentAnimLotteryActivity)  //跳到抽獎動畫
        }


        //按下抽獎按鈕後的畫面
        lotteryIcon(but_lottery, layout_lottery_result, true)
        lotteryAction(but_lottery_result, tV_lottery_result)
        but_start.setText("AGAIN")

    }

    private fun sharedPreSet(share_state: SharedPreferences, title: Button) {
        val shareData_1 = getSharedPreferences(GlobalVariable.data_1, MODE_PRIVATE)
        val shareData_2 = getSharedPreferences(GlobalVariable.data_2, MODE_PRIVATE)
        val shareData_3 = getSharedPreferences(GlobalVariable.data_3, MODE_PRIVATE)
        val shareData_4 = getSharedPreferences(GlobalVariable.data_4, MODE_PRIVATE)


        val data_state: Int = share_state.getInt("data_state", 1)
        Log.d(TAG, "data_state: $data_state")

        if (data_state == 1) {
            val dataName = shareData_1.getString("dataName", "DATA 1")
            title.setText(dataName)
            getItem(shareData_1)

        } else if (data_state == 2) {
            val dataName = shareData_2.getString("dataName", "DATA 2")
            title.setText(dataName)
            getItem(shareData_2)

        } else if (data_state == 3) {
            val dataName = shareData_3.getString("dataName", "DATA 3")
            title.setText(dataName)
            getItem(shareData_3)

        } else if (data_state == 4) {
            val dataName = shareData_4.getString("dataName", "DATA 4")
            title.setText(dataName)
            getItem(shareData_4)

        } else {
            title.setText("No find data")
        }
    }

    //抽獎的 開始/結果 畫面顯示 (開始畫面, 結束畫面, 判斷值)
    private fun lotteryIcon(start_icon: Button, result_icon: LinearLayout, lotteryFlag: Boolean) {
        if(lotteryFlag == true) {
            start_icon.visibility = View.GONE
            result_icon.visibility = View.VISIBLE
        }else {
            start_icon.visibility = View.VISIBLE
            result_icon.visibility = View.GONE
        }
    }

    //抽獎的數字的判斷 (彩球, 項目)
    private fun lotteryAction(result_but: Button, result_text: TextView) {
        val balls: Int = LotteryBall().getBallNumber(itemList)

        if (itemList.size == 0) {
            result_but.setText("0")
            result_text.setText("No data...")

        } else {
            result_but.setText(balls.toString())
            result_text.setText(itemList.get(balls - 1))
        }

        // 改變彩球的顏色
        if (balls % 4 == 0) {
            result_but.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_yellow)
            )
        } else if (balls % 4 == 3) {
            result_but.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_rad)
            )
        } else if (balls % 4 == 2) {
            result_but.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_blue)
            )
        } else if (balls % 4 == 1) {
            result_but.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_green)
            )
        }
    }

    private fun getItem(shareData: SharedPreferences) {
        itemList.clear()

        for (x in 0..itemCount-1) {
            val item = shareData.getString("item_${x+1}", "").toString()

            itemList.add(item)

            if (item.length == 0) {
                itemList.remove(item)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_exit, null)
        val but_yes = view.findViewById<Button>(R.id.but_yes)
        val but_no = view.findViewById<Button>(R.id.but_no)

        if(keyCode == KeyEvent.KEYCODE_BACK) {
            val bundle = AlertDialog.Builder(this).setView(view)
            val dialog = bundle.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            but_yes.setOnClickListener {
                finish()    //關閉APP
            }

            but_no.setOnClickListener {
                dialog.dismiss() //返回
            }

            return false    //防止一下按下按鈕就關閉
        }
        return super.onKeyDown(keyCode, event)
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
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        if(exitFlag == true) {
            finish()
        }
    }
//
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
//        if(exitFlag == false) {
//            System.exit(0)  //確保程式完全退出
//        }
    }
//--------------------------------------------------------------
}